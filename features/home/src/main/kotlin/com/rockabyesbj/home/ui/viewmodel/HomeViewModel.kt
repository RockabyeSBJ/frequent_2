package com.rockabyesbj.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockabyesbj.core.common.model.UserProfile
import com.rockabyesbj.core.error.AppError
import com.rockabyesbj.core.error.ErrorHandler
//import com.rockabyesbj.core.utils.CoroutineUtil
import com.rockabyesbj.home.ui.state.Deal
import com.rockabyesbj.home.ui.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            try {
                // Mock data for testing
                val mockProfile = UserProfile(
                    userId = "user123",
                    email = "john@example.com",
                    displayName = "John Doe",
                    avatarUrl = "https://example.com/avatar.jpg",
                    phoneNumber = "+1234567890",
                    loyaltyTier = "Gold",
                    dateJoined = System.currentTimeMillis(),
                    lastUpdated = System.currentTimeMillis()
                )

                val mockDeals = listOf(
                    Deal(
                        id = "deal1",
                        title = "Free Coffee",
                        description = "Get a free coffee with 100 points",
                        pointsRequired = 100,
                        imageUrl = null
                    ),
                    Deal(
                        id = "deal2",
                        title = "20% Off",
                        description = "20% off your next purchase with 200 points",
                        pointsRequired = 200,
                        imageUrl = null
                    )
                )
                
                _uiState.update { 
                    HomeUiState.Success(
                        userProfile = mockProfile,
                        loyaltyPoints = 150,
                        availableDeals = mockDeals
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    HomeUiState.Error(
                        message = "An unexpected error occurred. Please try again."
                    )
                }
            }
        }
    }

    fun retry() {
        _uiState.update { HomeUiState.Loading }
        loadHomeData()
    }

    fun dismissError() {
        _uiState.update { HomeUiState.Loading }
        loadHomeData()
    }
} 