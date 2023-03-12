package br.com.alura.forum.mapper

import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class ResponseTopicoMapper : Mapper<Topico, ResponseTopicoDTO> {
    override fun map(t: Topico): ResponseTopicoDTO {
        return ResponseTopicoDTO (
            id = t.id,
            titulo = t.mensagem,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status
        )
    }
}