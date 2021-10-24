package com.example.autenticacao

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
@EnableJpaRepositories
class AutenticacaoApplication

fun main(args: Array<String>) {
    runApplication<AutenticacaoApplication>(*args)
}

@Bean
fun getPasswordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
}
