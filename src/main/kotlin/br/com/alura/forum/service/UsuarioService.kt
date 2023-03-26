package br.com.alura.forum.service

import br.com.alura.forum.config.UserDetailsImpl
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository): UserDetailsService {

    fun buscarPorId(id: Long): Usuario = repository.findById(id)
           .orElseThrow { NotFoundException("Usuario não encontrado") }

    fun buscar(): List<Usuario> = repository.findAll()

    override fun loadUserByUsername(email: String?): UserDetails {
        val usuario: Usuario = repository.findByEmail(email) ?: throw NotFoundException("Usuario não encontrado")

        return UserDetailsImpl(usuario)
    }
}
