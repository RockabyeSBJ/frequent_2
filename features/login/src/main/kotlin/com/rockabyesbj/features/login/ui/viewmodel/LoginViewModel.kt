package com.rockabyesbj.login.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockabyesbj.core.error.AppError
import com.rockabyesbj.core.error.ErrorHandler
import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.login.ui.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: IAuthSessionManager,
    private val errorHandler: ErrorHandler
): ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onLoginClicked(activity: Activity) {
        if (_uiState.value is LoginUiState.Loading) return
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                withTimeout(30_000L) {
                    val success = sessionManager.signIn(activity)
                    _uiState.value = if (success) {
                        LoginUiState.Success
                    } else {
                        LoginUiState.Error("Login was cancelled or failed")
                    }
                }
            } catch (e: Exception) {
                val appError = errorHandler.handleException(e)
                _uiState.value = LoginUiState.Error(
                    when (appError) {
                        is AppError.Timeout -> "Login timed out. Please try again."
                        is AppError.Network -> "Network error. Please check your connection."
                        is AppError.Authentication -> "Authentication failed. Please try again."
                        is AppError.ServerError -> "Server error. Please try again later."
                        is AppError.Unknown -> "Login failed: ${appError.message ?: "Unknown error"}"
                        else -> "Login failed: ${e.message ?: "Unknown error"}"
                    }
                )
            }
        }
    }

fun onErrorDismissed() {
        if (_uiState.value is LoginUiState.Error) {
            _uiState.value = LoginUiState.Idle
        }
    }

    fun logout() {
        // Optional: If needed, trigger repository.signOut()
        _uiState.value = LoginUiState.Idle
    }

    fun onNavigationHandled() {
        _uiState.value = LoginUiState.Idle
    }
}
