package br.com.alura.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AtualizacaoTopicoDTO (
    @field:NotNull
    val id: Long,

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String
)
