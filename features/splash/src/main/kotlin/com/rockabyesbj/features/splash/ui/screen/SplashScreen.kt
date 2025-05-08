package com.rockabyesbj.features.splash.ui.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rockabyesbj.features.splash.ui.viewmodel.SplashViewModel
import com.rockabyesbj.features.splash.ui.state.SplashUiState

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState) {
            is SplashUiState.NavigateToHome -> onNavigateToHome()
            is SplashUiState.NavigateToLogin -> onNavigateToLogin()
            SplashUiState.Loading -> Unit // no-op, spinner shown
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
    }
}
