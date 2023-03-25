package br.com.alura.forum.service

import br.com.alura.forum.dto.request.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RequestTopicoMapper
import br.com.alura.forum.mapper.ResponseTopicoMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val responseTopicoMapper: ResponseTopicoMapper,
    private val requestTopicoMapper: RequestTopicoMapper,
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<ResponseTopicoDTO> {
        return Optional.ofNullable(nomeCurso)
            .map { nome -> repository.findByCursoNome(nome, paginacao) }
            .orElseGet { repository.findAll(paginacao) }
            .map { responseTopicoMapper.map(it) }
    }

    fun buscarPorId(id: Long): ResponseTopicoDTO? =
        repository.findById(id)
            .map { responseTopicoMapper.map(it) }
            .orElseThrow { NotFoundException("Topico não encontrado") }

    fun cadastrar(requestTopicoDTO: RequestTopicoDTO): ResponseTopicoDTO {
        val result: Topico = requestTopicoMapper.map(requestTopicoDTO)

        val response: Topico = repository.save(result)

        return responseTopicoMapper.map(response)
    }

    fun atualizar(atualizacaoTopicoDTO: AtualizacaoTopicoDTO): ResponseTopicoDTO {
        return repository
            .findById(atualizacaoTopicoDTO.id)
            .map { topico ->
                topico.titulo = atualizacaoTopicoDTO.titulo
                topico.mensagem = atualizacaoTopicoDTO.mensagem
                responseTopicoMapper.map(topico) }
            .orElseThrow { NotFoundException("Topico não encontrado") }
    }

    fun deletar(id: Long) = repository.deleteById(id)
}