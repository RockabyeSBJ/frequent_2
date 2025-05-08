package com.rockabyesbj.core.auth.network

/**
 * [IAuthRemoteDataSource] defines the contract for authentication-related network operations
 * such as email/password sign-in and token refresh.
 *
 * ❗ Currently not used. This interface is reserved for future separation of network logic from
 * domain and view layers, to support clean testing, mocking, and modularity.
 *
 * If reintroduced, this should be implemented by a Retrofit-based repository (e.g., AuthRepository)
 * and consumed via domain-layer abstractions like IAuthSessionManager or use cases.
 */
