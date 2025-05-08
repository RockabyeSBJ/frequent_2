package com.rockabyesbj.core.common.session

//If you don’t need createdAt anymore (e.g. because you’re decoding exp from the JWT itself), then:
//Remove createdAt from: UserSessionEntity, UserSessionRepository, and UserSessionDao.
//I don't really know what I'm doing yet with jwtutil, but I'll add a note there too. Looks like the original intent was to decode here.

import com.rockabyesbj.core.common.utils.JwtUtil

data class UserSession(
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Long
) {

    private val claims: JwtUtil.JwtClaims? by lazy {
        JwtUtil.decodeClaims(accessToken)
    }

    val userId: String? get() = claims?.subject
    val email: String? get() = claims?.email
    val userType: String? get() = claims?.userType
    val expiresAt: Long? get() = claims?.expiresAt
    val isExpired: Boolean
        get() = JwtUtil.isTokenExpired(accessToken)
}