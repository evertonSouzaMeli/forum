package br.com.alura.forum.controller

import br.com.alura.forum.dto.request.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.service.TopicoService
import javax.transaction.Transactional
import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController (private val topicoService: TopicoService) {

    @GetMapping
    fun lista() : List<ResponseTopicoDTO> =  topicoService.listar()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseTopicoDTO? = topicoService.buscarPorId(id)

    @PostMapping
    @Transactional
    fun cadastrar(
        @Valid @RequestBody requestTopicoDTO: RequestTopicoDTO,
        uriComponentsBuilder: UriComponentsBuilder) : ResponseEntity<ResponseTopicoDTO> {
        val response = topicoService.cadastrar(requestTopicoDTO)

        val uri = uriComponentsBuilder.path("/topicos/${response.id}").build().toUri()

        return ResponseEntity.created(uri).body(response)
    }

    @PutMapping
    @Transactional
    fun atualizar(@Valid @RequestBody atualizacaoTopicoDTO: AtualizacaoTopicoDTO) {
        topicoService.atualizar(atualizacaoTopicoDTO)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }
}