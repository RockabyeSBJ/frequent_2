package com.rockabyesbj.features.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rockabyesbj.features.splash.ui.screen.SplashScreen
import com.rockabyesbj.features.splash.ui.viewmodel.SplashViewModel
import com.rockabyesbj.core.navigation.FeatureEntry

const val SplashRoute = "splash"

object SplashFeatureEntry : FeatureEntry {
    override val route: String = SplashRoute

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onSessionChecked: ((String) -> Unit)?  //  <--according to best practice, better to declare here, not AppNavGraph
    ) {
        navGraphBuilder.composable(route = "splash") {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel = viewModel,
                onNavigateToHome = {
                    onSessionChecked?.invoke("home") ?: navController.navigate("home") {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    onSessionChecked?.invoke("login") ?: navController.navigate("login") {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}
