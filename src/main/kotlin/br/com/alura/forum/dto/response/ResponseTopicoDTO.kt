package br.com.alura.forum.dto.response

import br.com.alura.forum.enums.StatusTopico
import java.time.LocalDateTime

data class ResponseTopicoDTO (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
