package com.rockabyesbj.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rockabyesbj.home.ui.screen.HomeScreen
import com.rockabyesbj.home.ui.viewmodel.HomeViewModel

sealed class HomeScreen(val route: String) {
    object Main : HomeScreen("home/home")
    object Loyalty : HomeScreen("home/loyalty")
    object Profile : HomeScreen("home/profile")
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    composable(route = HomeScreen.Main.route) { backStackEntry ->
        val viewModel: HomeViewModel = hiltViewModel(backStackEntry)
        HomeScreen(
            viewModel = viewModel,
            navigateTo = { route -> navController.navigate(route) }
        )
    }
}
