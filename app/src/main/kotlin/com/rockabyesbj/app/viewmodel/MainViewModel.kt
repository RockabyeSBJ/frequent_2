package com.rockabyesbj.app.viewmodel

// MainViewModel: Global App State (when needed)
//
// - // Theme settings (light/dark/system)
// - // Locale or language preferences
// - // Global loading state (for overlays or modal spinners)
// - // Error state management (snackbars, toasts, retry events)
// - // Feature flags or remote config integration
// - // App-wide user event logging / analytics hooks
// - // Push notification registration state
// - // App version checks / forced upgrade logic
// - // Session-wide timers, inactivity tracking, or locks
// - // Connectivity state monitoring (offline/online logic)
//
// Note: Only use MainViewModel for non-screen-specific state.
// Keep individual feature ViewModels scoped to each composable.


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel()
