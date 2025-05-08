package com.rockabyesbj.features.splash.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockabyesbj.core.auth.interfaces.ISessionManager
import com.rockabyesbj.features.splash.ui.state.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: ISessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        restoreSession()
    }

    private fun restoreSession() {
        viewModelScope.launch {
            try {
                sessionManager.restoreSession()
                if (sessionManager.isLoggedIn) {
                    _uiState.value = SplashUiState.NavigateToHome
                } else {
                    _uiState.value = SplashUiState.NavigateToLogin
                }
            } catch (e: Exception) {
                _uiState.value = SplashUiState.NavigateToLogin
            }
        }
    }
}
