package br.com.alura.forum.service

import br.com.alura.forum.dto.request.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RequestTopicoMapper
import br.com.alura.forum.mapper.ResponseTopicoMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico>,
    private val responseTopicoMapper: ResponseTopicoMapper,
    private val requestTopicoMapper: RequestTopicoMapper,
) {

    fun listar(): List<ResponseTopicoDTO> = topicos.stream()
        .map { responseTopicoMapper.map(it) }
        .collect(Collectors.toList())

    fun buscarPorId(id: Long): ResponseTopicoDTO? = topicos.stream()
        .filter { it.id == id }
        .findFirst()
        .map { responseTopicoMapper.map(it) }
        .orElseThrow { NotFoundException("Topico não encontrado") }

    fun cadastrar(requestTopicoDTO: RequestTopicoDTO) : ResponseTopicoDTO {
        topicos = topicos.plus(requestTopicoMapper.map(requestTopicoDTO)
            .apply { this.id = topicos.size.plus(1L) })

        return responseTopicoMapper.map(requestTopicoMapper.map(requestTopicoDTO))
    }

    fun atualizar(atualizacaoTopicoDTO: AtualizacaoTopicoDTO) {
        val topico = topicos.stream()
            .filter { it.id == atualizacaoTopicoDTO.id }
            .findFirst()
            .orElseThrow { NotFoundException("Topico não encontrado") }

        topicos = topicos.minus(topico)
            .plus(Topico(
                    id = atualizacaoTopicoDTO.id,
                    titulo = atualizacaoTopicoDTO.titulo,
                    mensagem = atualizacaoTopicoDTO.mensagem,
                    autor = topico.autor,
                    curso = topico.curso,
                    respostas = topico.respostas,
                    status = topico.status,
                    dataCriacao = topico.dataCriacao
                )
            )
    }

    fun deletar(id: Long) {
        val topico = topicos.stream()
            .filter { it.id == id }
            .findFirst()
            .orElseThrow { NotFoundException("Topico não encontrado") }

        topicos = topicos.minus(topico)
    }
}