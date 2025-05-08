package com.rockabyesbj.features.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.rockabyesbj.core.navigation.FeatureEntry

// Route constant (optional but useful for consistency)
const val ProfileRoute = "profile"

object ProfileFeatureEntry : FeatureEntry {
    override val route = ProfileRoute

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onSessionChecked: ((String) -> Unit)? // not used
    ) {
        navGraphBuilder.profileNavGraph(navController)
    }
}
