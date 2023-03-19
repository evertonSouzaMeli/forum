package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario = repository.findById(id)
           .orElseThrow{ NotFoundException("Usuario não encontrado") }

    fun buscar(): List<Usuario> = repository.findAll()
}
