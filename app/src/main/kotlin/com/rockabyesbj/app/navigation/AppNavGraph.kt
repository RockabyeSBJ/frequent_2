package com.rockabyesbj.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rockabyesbj.features.login.navigation.LoginFeatureEntry
import com.rockabyesbj.features.splash.navigation.SplashFeatureEntry

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onSessionChecked: ((String) -> Unit)? = null
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        SplashFeatureEntry.register(
            navGraphBuilder = this,
            navController = navController,
            onSessionChecked = onSessionChecked
        )

        LoginFeatureEntry.register(
            navGraphBuilder = this,
            navController = navController,
            onSessionChecked = onSessionChecked // ✅ passed even if unused
        )
        //ProfileFeatureEntry.register(this, navController)
        //LoyaltyFeatureEntry.register(this, navController)
    }
}

