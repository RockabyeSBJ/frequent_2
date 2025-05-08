package com.rockabyesbj.features.login.ui.state

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
    object NavigateToHome : LoginUiState
}
