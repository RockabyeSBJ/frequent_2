package com.rockabyesbj.core.auth.model

import com.microsoft.identity.client.exception.MsalException

sealed class TokenResult {
    data class Success(val token: String) : TokenResult()
    object UiRequired : TokenResult()
    object NoAccount : TokenResult()
    data class Failure(val exception: MsalException) : TokenResult()
}
