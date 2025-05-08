package com.rockabyesbj.home.ui.state

import com.rockabyesbj.core.common.model.UserProfile

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val userProfile: UserProfile,
        val loyaltyPoints: Int,
        val availableDeals: List<Deal>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

data class Deal(
    val id: String,
    val title: String,
    val description: String,
    val pointsRequired: Int,
    val imageUrl: String?
) 