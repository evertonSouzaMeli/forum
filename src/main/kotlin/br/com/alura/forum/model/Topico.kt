package br.com.alura.forum.model

import br.com.alura.forum.enums.StatusTopico
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY
import java.time.LocalDateTime

@Entity
data class Topico (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", )
    val curso: Curso,

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", )
    val autor: Usuario,

    @Enumerated(STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @OneToMany(mappedBy = "topico", cascade = [CascadeType.ALL])
    val respostas: List<Resposta> = ArrayList()
)