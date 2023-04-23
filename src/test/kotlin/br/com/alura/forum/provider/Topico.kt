package br.com.alura.forum.provider

import br.com.alura.forum.enums.StatusTopico
import br.com.alura.forum.model.Topico

import java.time.LocalDateTime

object Topico {
    fun build() = Topico(
        id = 1L,
        titulo = "Topico teste",
        mensagem = "Mensagem teste",
        dataCriacao = LocalDateTime.now(),
        curso = Curso.build(),
        autor = Usuario.build(),
        status = StatusTopico.NAO_RESPONDIDO
    )
}