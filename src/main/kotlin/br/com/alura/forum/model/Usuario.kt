package br.com.alura.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany

@Entity
data class Usuario(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = EAGER)
    @JoinColumn(name = "usuario_roles")
    val roles: List<Role> = mutableListOf()
)
