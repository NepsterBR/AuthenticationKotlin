package com.example.autenticacao.service

import com.example.autenticacao.entity.Client
import com.example.autenticacao.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.SecretKey

@Service
@Component
class UserService(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) {

    //TODO colocar em variavel de ambiente
    private val KEY: SecretKey = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .toByteArray(StandardCharsets.UTF_8))

    fun creatUser(user: Client): String {
        return if (userRepository.findByLogin(user.login)?.isPresent == true) {
            "login já cadastrado no sistema"
        } else try {
            user.password = passwordEncoder.encode(user.password)
            userRepository.save(user)
            "Usuario criado com sucesso!"
        } catch (e: Exception) {
            "não foi possivel criar usuario"
        }
    }

    fun validateUser(user: Client): String {
        val name: Optional<Client?>? = userRepository.findByLogin(user.login)
        return try {
            if (!passwordEncoder.matches(user.password, name!!.get().password)) {
                "Usuário e/ou senha inválidos."
            } else {
                Jwts.builder()
                        .setSubject(name.get().toString())
                        .setIssuer("Let's Code Bank")
                        .setIssuedAt(Date())
                        .setExpiration(
                                Date.from(
                                        LocalDateTime.now().plusMinutes(15L)
                                                .atZone(ZoneId.systemDefault())
                                                .toInstant()))
                        .signWith(SignatureAlgorithm.HS256, KEY)
                        .compact()
            }
        } catch (ex: Exception) {
            "Não foi possivel validar o usuário."
        }
    }
}