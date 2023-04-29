package br.com.alura.forum.integration

import br.com.alura.forum.provider.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TopicoRepositoryTest(
    @Autowired
    private val repository: TopicoRepository
) {

    companion object {
        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:8.0.28")
            .apply {
                withDatabaseName("testedb")
                withUsername("root")
                withPassword("root")
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
        }
    }

    @Test
    fun `deve gerar um retornar um objeto salvo`() {
        repository.save(Topico.build())

        val relatorio = repository.findByCursoNome("Kotlin", Pageable.unpaged()).get().findFirst()

        assertNotNull(relatorio)

        assertEquals("Topico teste", relatorio.get().titulo)

        assertEquals("Mensagem teste", relatorio.get().mensagem)
    }
}