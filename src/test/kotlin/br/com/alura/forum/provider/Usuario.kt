package br.com.alura.forum.provider

import br.com.alura.forum.model.Usuario

object Usuario {
    fun build() = Usuario(
        id = 1L,
        nome = "nome",
        email = "email@email",
        password = "admin123",
    )
}