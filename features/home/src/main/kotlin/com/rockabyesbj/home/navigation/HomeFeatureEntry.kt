package com.rockabyesbj.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.rockabyesbj.core.navigation.FeatureEntry

object HomeFeatureEntry : FeatureEntry {
    override val route = "home"

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onSessionChecked: ((String) -> Unit)? // not used
    ) {
        navGraphBuilder.homeNavGraph(navController)
    }
} 