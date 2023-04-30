package br.com.alura.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Resposta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val mensagem: String,

    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", )
    val autor: Usuario,

    @ManyToOne
    @JoinColumn(name = "topico_id", referencedColumnName = "id", )
    val topico: Topico,

    val solucao: Boolean
)