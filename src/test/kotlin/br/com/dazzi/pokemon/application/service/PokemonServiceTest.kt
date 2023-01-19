package br.com.dazzi.pokemon.application.service

import br.com.dazzi.pokemon.application.dto.PokemonDTO
import br.com.dazzi.pokemon.application.port.out.PokemonOutputPort
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PokemonServiceTest {

    @MockK
    private lateinit var pokemonOutputPort : PokemonOutputPort

    private val pokemonService: PokemonService

    init {
        MockKAnnotations.init(this)
        pokemonService = PokemonService(pokemonOutputPort)
    }

    @Test
    fun `should find pokemon by ID correctly`() {
        every { pokemonOutputPort.findPokemonByNum(4) } returns
                PokemonDTO(
                    num = 4,
                    name = "charmander",
                    types = setOf("fire")
                )

        pokemonService.findPokemonByNum(4).let {
            it.name shouldBe "charmander"
            it.num shouldBe 4
            it.types shouldBe setOf("fire")
        }

        verify(exactly = 1){
            pokemonOutputPort.findPokemonByNum(withArg {
                it shouldBe 4
            })
        }

    }
}