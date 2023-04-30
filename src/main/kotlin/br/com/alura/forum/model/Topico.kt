package br.com.alura.forum.model

import br.com.alura.forum.enums.StatusTopico
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var titulo: String,

    var mensagem: String,

    val dataCriacao: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", )
    val curso: Curso,

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", )
    val autor: Usuario,

    @Enumerated(EnumType.STRING)
    val status: StatusTopico,

    @OneToMany(mappedBy = "topico", cascade = [CascadeType.ALL])
    val respostas: List<Resposta> = ArrayList()
)