package com.example.autenticacao.service

import com.example.autenticacao.entity.Audit
import com.example.autenticacao.entity.RegisterType
import com.example.autenticacao.repository.AuditRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuditService {
    @Autowired
    lateinit var auditRepository: AuditRepository

    fun convertAudit(login: String, type: RegisterType){
        val id = UUID.randomUUID()
        val now = System.currentTimeMillis()
        auditRepository.save(Audit(id, type.toString(), login, now))
    }

    fun getAudit(): List<Audit> {
        return auditRepository.findAll()
            .sortedBy { a -> a.login }
    }
}