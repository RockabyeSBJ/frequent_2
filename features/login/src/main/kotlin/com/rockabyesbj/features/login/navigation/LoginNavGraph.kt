package com.rockabyesbj.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rockabyesbj.features.login.ui.screen.LoginScreen

// Feature-local sealed class defining screen routes in this nav graph
sealed class LoginScreen(val route: String) {
    object Login : LoginScreen("login")
    // object ForgotPassword : LoginScreen("login/forgot-password") // Future extension
}

fun NavGraphBuilder.loginNavGraph(navController: NavHostController) {
    composable("login") {
        LoginScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }
}

    // TODO: Add ForgotPasswordScreen and navigation route when Forgot Password flow is implemented