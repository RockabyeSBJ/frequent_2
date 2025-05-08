package com.rockabyesbj.features.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rockabyesbj.features.profile.ui.screen.ProfileScreen
import com.rockabyesbj.features.profile.ui.viewmodel.ProfileViewModel

sealed class ProfileScreenRoute(val route: String) {
    object Main : ProfileScreenRoute("profile/main")
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    composable(ProfileScreenRoute.Main.route) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(viewModel = viewModel)
    }

    // TODO: Add SettingsScreen or Loyalty tier views here in the future
}
