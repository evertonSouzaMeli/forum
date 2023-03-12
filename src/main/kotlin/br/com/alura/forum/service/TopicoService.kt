package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
        topicos = arrayListOf(
            Topico( id = 1,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis Kotlin",
                curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
                autor = Usuario(id = 1, nome = "Ana da Silva", email = "ana@email.com")
            ),

            Topico( id = 2,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis Kotlin 2",
                curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
                autor = Usuario(id = 1, nome = "Ana da Silva", email = "ana@email.com")
            ),

            Topico( id = 3,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis Kotlin 3",
                curso = Curso(id = 1, nome = "Kotlin", categoria = "Programação"),
                autor = Usuario(id = 1, nome = "Ana da Silva", email = "ana@email.com")
            )
        )
    }

    fun listar(): List<Topico> = topicos
    fun buscarPorId(id: Long): Topico? {
        return topicos.stream()
            .filter { it.id == id }
            .findFirst()
            .orElse(null)
    }
}