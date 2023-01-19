package br.com.dazzi.pokemon.adapter.`in`.rest.dto

import br.com.dazzi.pokemon.application.dto.PokemonDTO
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class PokemonResponseTest{


    @Test fun `should instantiate PokemonResponse correctly`(){
        val poke = PokemonResponse<PokemonDTO>(
            status = 888,
            message = "Something",
            result = null
        )

        poke.message shouldNotBe null
        poke.message shouldBe "Something"

        poke.result shouldBe null

        poke.status shouldBe 888
    }

}