package br.com.alura.forum.controller

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController (private val topicoService: TopicoService) {

    @GetMapping
    fun lista() : List<Topico> = topicoService.listar()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico? = topicoService.buscarPorId(id)
}