package com.example.autenticacao.controller

import com.example.autenticacao.dto.request.UserRequest
import com.example.autenticacao.dto.response.TokenResponse
import com.example.autenticacao.entity.Audit
import com.example.autenticacao.entity.Client
import com.example.autenticacao.service.AuditService
import com.example.autenticacao.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        val userService: UserService,
        val auditService: AuditService
) {

    @PostMapping("/user")
    fun creatUser(@RequestBody userRequest: Client): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest.let { userService.creatUser(it) })
    }

    @PostMapping("/autentication")
    fun validationUser(@RequestBody userRequest: UserRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userRequest.let { userService.validateUser(it) })
    }

    @GetMapping("/audit")
    fun getAudit(): List<Audit>{
        return auditService.getAudit()
    }
}