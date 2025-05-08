package com.rockabyesbj.core.common.utils

import java.util.UUID

object IdGenerator {
    fun generateUUID(): String = UUID.randomUUID().toString()

    fun generateShortCode(length: Int = 6): String {
        val chars = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}
