package br.com.alura.forum.service

import br.com.alura.forum.dto.request.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RequestTopicoMapper
import br.com.alura.forum.mapper.ResponseTopicoMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val responseTopicoMapper: ResponseTopicoMapper,
    private val requestTopicoMapper: RequestTopicoMapper,
) {

    fun listar(): List<ResponseTopicoDTO> = repository.findAll().map { responseTopicoMapper.map(it) }

    fun buscarPorId(id: Long): ResponseTopicoDTO? =
        repository.findById(id)
        .map { responseTopicoMapper.map(it) }
        .orElseThrow { NotFoundException("Topico não encontrado") }

    fun cadastrar(requestTopicoDTO: RequestTopicoDTO) : ResponseTopicoDTO {
        val response: Topico = repository.save(requestTopicoMapper.map(requestTopicoDTO))
        return responseTopicoMapper.map(response)
    }

    fun atualizar(atualizacaoTopicoDTO: AtualizacaoTopicoDTO) : ResponseTopicoDTO {
        var topico = repository
            .findById(atualizacaoTopicoDTO.id)
            .orElseThrow { NotFoundException("Topico não encontrado") }

        topico.titulo = atualizacaoTopicoDTO.titulo
        topico.mensagem = atualizacaoTopicoDTO.mensagem

        return responseTopicoMapper.map(topico)
    }

    fun deletar(id: Long) = repository.deleteById(id)
}