package com.rockabyesbj.features.profile.ui.state

import com.rockabyesbj.core.common.model.UserProfile
import com.rockabyesbj.core.common.session.UserSession

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profile: UserProfile? = null,
    val session: UserSession? = null, // ✅ Add this line
    val error: String? = null
)
