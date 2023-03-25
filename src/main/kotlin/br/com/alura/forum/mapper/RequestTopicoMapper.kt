package br.com.alura.forum.mapper

import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.enums.StatusTopico
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RequestTopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
): Mapper<RequestTopicoDTO, Topico> {

    override fun map(t: RequestTopicoDTO): Topico {
        val curso: Curso = cursoService.buscarPorId(t.idCurso)
        val autor: Usuario = usuarioService.buscarPorId(t.idAutor)

        return Topico (
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = LocalDateTime.now(),
            status  = StatusTopico.NAO_RESPONDIDO,
            curso = curso,
            autor = autor
        )
    }
}