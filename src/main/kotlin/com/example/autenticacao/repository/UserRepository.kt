package com.example.autenticacao.repository

import com.example.autenticacao.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<Client?, Long?> {
    fun findByLogin(login: String): Optional<Client?>
}