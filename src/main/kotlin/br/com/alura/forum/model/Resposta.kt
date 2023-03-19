package br.com.alura.forum.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Resposta (
    @Id
    @GeneratedValue(strategy = IDENTITY)
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