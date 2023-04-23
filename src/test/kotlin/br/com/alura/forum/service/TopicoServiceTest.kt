package br.com.alura.forum.service

import br.com.alura.forum.dto.response.ResponseTopicoDTO
import br.com.alura.forum.mapper.RequestTopicoMapper
import br.com.alura.forum.mapper.ResponseTopicoMapper
import br.com.alura.forum.provider.Topico
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopicoServiceTest {

    @MockK
    private lateinit var requestTopicoMapper: RequestTopicoMapper

    @MockK
    private lateinit var responseTopicoMapper: ResponseTopicoMapper

    @MockK
    private lateinit var repository: TopicoRepository

    @InjectMockKs
    private lateinit var service: TopicoService

    @BeforeAll
    fun init() {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `should return all result when the nomeCurso parameter is missing `() {
        every { repository.findAll(Pageable.unpaged()) } returns PageImpl(listOf())

        val result = service.listar(null, Pageable.unpaged())

        assertEquals(Page.empty<ResponseTopicoDTO>(), result)

        verify(exactly = 1) { repository.findAll(Pageable.unpaged()) }
    }

    @Test
    fun `should all result when the nomeCurso parameter is missing `() {
        val slot = slot<br.com.alura.forum.model.Topico>()

        every { repository.findByCursoNome(any(), any()) } returns PageImpl(listOf(Topico.build()))

        every { repository.findAll(Pageable.unpaged()) } returns Page.empty()

        every { responseTopicoMapper.map(capture(slot)) } answers { callOriginal() }

        val result = service.listar("Kotlin", Pageable.unpaged())

        assertNotNull(result.get().findFirst())

        assertEquals(slot.captured.mensagem, result.get().findFirst().get().mensagem)

        verify(exactly = 1) { responseTopicoMapper.map(any()) }

        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
    }
}