package br.com.alura.forum.provider

import br.com.alura.forum.model.Curso

object Curso {
    fun build() = Curso(
        id = 1L,
        nome = "Kotlin",
        categoria = "Programação"
    )
}