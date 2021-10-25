package com.example.autenticacao.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

class UserRequest (
        val login: String,
        @JsonProperty("senha")
        var password: String = ""
)
