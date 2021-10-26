package com.example.autenticacao.repository

import com.example.autenticacao.entity.Audit
import org.springframework.data.jpa.repository.JpaRepository

interface AuditRepository: JpaRepository<Audit, String> {
}