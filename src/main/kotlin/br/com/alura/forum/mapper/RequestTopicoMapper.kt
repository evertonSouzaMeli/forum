package br.com.alura.forum.mapper

import br.com.alura.forum.dto.request.RequestTopicoDTO
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RequestTopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
): Mapper<RequestTopicoDTO, Topico> {

    override fun map(t: RequestTopicoDTO): Topico {
        return Topico (
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }
}