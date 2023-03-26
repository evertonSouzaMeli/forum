package br.com.alura.forum.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private val id: Long,
    private val nome: String
): GrantedAuthority {

    override fun getAuthority(): String = nome
}