package com.rockabyesbj.features.profile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockabyesbj.core.auth.interfaces.ISessionRepository
import com.rockabyesbj.core.auth.interfaces.ISessionManager
import com.rockabyesbj.core.auth.interfaces.IUserProfileRepository
import com.rockabyesbj.core.common.session.UserSession
import com.rockabyesbj.features.profile.ui.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileRepository: IUserProfileRepository, // ✅ Now interface
    private val userSessionRepository: ISessionRepository,
    private val sessionManager: ISessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeSession()
    }

    fun loadProfile(userId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                val profile = userProfileRepository.getUserProfile(userId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    profile = profile,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error loading profile"
                )
            }
        }
    }

    private fun observeSession() {
        viewModelScope.launch {
            sessionManager.userSession.collect { session: UserSession? ->
                _uiState.value = _uiState.value.copy(session = session)
            }
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                userSessionRepository.clearUserSession()
                sessionManager.clearSession()
            } catch (e: Exception) {
                // Optionally log or handle logout failure
            } finally {
                onLogoutComplete()
            }
        }
    }
}