package com.rockabyesbj.features.splash.ui.state

sealed interface SplashUiState {
    object Loading : SplashUiState
    object NavigateToLogin : SplashUiState
    object NavigateToHome : SplashUiState
}
