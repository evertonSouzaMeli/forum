package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        usuarios = arrayListOf(
            Usuario (
                    id = 1,
                    nome = "Ana da Silva",
                    email = "ana@email.com"
            )
        )
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter{ it.id == id}.findFirst().get()
    }

}
