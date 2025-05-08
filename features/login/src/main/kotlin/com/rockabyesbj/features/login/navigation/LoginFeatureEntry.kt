package com.rockabyesbj.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.rockabyesbj.core.navigation.FeatureEntry
import com.rockabyesbj.features.login.navigation.loginNavGraph

object LoginFeatureEntry : FeatureEntry {
    override val route = "login"

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onSessionChecked: ((String) -> Unit)? // 👈 must be here even if unused
    ) {
        navGraphBuilder.loginNavGraph(navController) // ✅ ignore it safely
    }
}


