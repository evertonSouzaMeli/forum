package br.com.alura.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RequestTopicoDTO (
    @field:NotEmpty(message = "Titulo não pode ser vazia")
    @field:Size(min = 5, max = 100)
    val titulo: String,

    @field:NotEmpty(message = "Mensagem não pode ser vazia")
    @field:Size(min = 5, max = 255)
    val mensagem: String,

    @field:NotNull(message = "Curso não pode ser nulo")
    val idCurso: Long,

    @field:NotNull(message = "Usuario não pode ser nulo")
    val idAutor: Long
)