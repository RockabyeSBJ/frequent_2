package com.rockabyesbj.features.profile.ui.screen

import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rockabyesbj.core.common.session.UserSession
import com.rockabyesbj.core.common.model.UserProfile
import com.rockabyesbj.features.profile.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val profile = uiState.profile
    val error = uiState.error

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Error loading profile: $error",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        profile != null -> {
            ProfileContent(
                userProfile = profile,
                userSession = uiState.session
            )
        }
    }
}

@Composable
fun ProfileContent(
    userProfile: UserProfile,
    userSession: UserSession?
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TODO: Add image loader if you want to show avatar
        /*
        userProfile.avatarUrl?.let { avatarUrl ->
            AsyncImage(
                model = avatarUrl,
                contentDescription = "User Avatar",
                modifier = Modifier.size(120.dp)
            )
        }
        */

        Text(
            text = userProfile.displayName ?: "No Name",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Text(
            text = userProfile.email ?: "No email provided",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Debug Info:",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Profile User ID: ${userProfile.userId}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        userSession?.let { session ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Session User ID: ${session.userId}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Session Expires At: ${session.expiresAt}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = "AccessToken: ${session.accessToken.take(30)}...",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        } ?: Text(
            text = "No Session Found.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}
