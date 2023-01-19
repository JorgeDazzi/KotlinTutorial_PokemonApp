package br.com.dazzi.pokemon.adapter.`in`.rest

import br.com.dazzi.pokemon.application.dto.PokemonDTO
import br.com.dazzi.pokemon.application.service.PokemonService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

@WebMvcTest
internal class PokemonControllerTest(
    @Autowired
    private val mvc: MockMvc
){

    @MockkBean
    private lateinit var service: PokemonService

    @Test fun `should perform a get call in app correctly`(){
        //{"status":200,"message":null,"result":{"num":4,"name":"charmander","types":["fire"]}}

        every { service.findPokemonByNum(4) } returns
                PokemonDTO(num = 4, name = "charmander", types = setOf("fire"))

        mvc.get("/pokemon/4")
            .andDo { MockMvcResultHandlers.print() }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("\$.status") { value(200)} }
            .andExpect { jsonPath("\$.message") { value(null)} }
            .andExpect { jsonPath("\$.result.num") { value(4)} }
            .andExpect { jsonPath("\$.result.name") { value("charmander")} }
            .andExpect { jsonPath("\$.result.types.[0]") { value("fire")} }

        verify(exactly = 1){
            service.findPokemonByNum(4)
        }
    }



}