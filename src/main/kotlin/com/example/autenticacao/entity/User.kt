package com.example.autenticacao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "clients")
data class Client(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_user")
        @JsonIgnore
        val idUser: Long = 0L,
        val name: String = "",
        val email: String = "",
        val cpf_cnpj: String = "",
        val login: String = "",
        @JsonProperty("senha")
        var password: String = "",
        @JsonIgnore
        val uuid: UUID = UUID.randomUUID()

)

@Entity
@Table(name = "audit")
data class Audit(

        @Id
        val id: UUID = UUID.randomUUID(),
        val type: String = "",
        val login: String = "",
        @Column(name = "created_at")
        val createdAt: Long = 0
        )

enum class RegisterType{ CADASTRO, AUTENTICACAO }