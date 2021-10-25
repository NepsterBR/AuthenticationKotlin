package com.example.autenticacao.dto.response

import java.util.*

class TokenResponse(
        val token: String,
        val expiresIn: Long
)
