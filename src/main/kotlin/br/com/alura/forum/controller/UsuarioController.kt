package br.com.alura.forum.controller

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val service: UsuarioService) {

    @GetMapping
    fun buscar() : ResponseEntity<List<Usuario>> {
        return ResponseEntity.ok().body(service.buscar())
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) : ResponseEntity<Usuario> {
        return ResponseEntity.ok().body(service.buscarPorId(id))
    }
}