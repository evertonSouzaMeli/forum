package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(var cursos: List<Curso>) {

    init {
        cursos = arrayListOf(
            Curso(
                    id = 1,
                    nome = "Kotlin",
                    categoria = "Programação"
            ),
            Curso(
                    id = 2,
                    nome = "Java",
                    categoria = "Programação"
            )
        )
    }


    fun buscarPorId(id: Long): Curso = cursos.stream().filter{ it.id == id}.findFirst().get()
}
