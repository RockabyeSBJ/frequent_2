package com.rockabyesbj.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureEntry {
    val route: String

    /**
     * Default register method for features that don't need session handling.
     */
    fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,

    ) {
        register(navGraphBuilder, navController, null)
    }

    /**
     * Optional session-aware register override.
     * Features like splash or login can use the `onSessionChecked` callback.
     */
    fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onSessionChecked: ((String) -> Unit)?
    )
}
