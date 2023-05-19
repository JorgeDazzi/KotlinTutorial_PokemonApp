package br.com.dazzi.pokemon.adapter.`in`.rest

import br.com.dazzi.pokemon.PokemonApplication
import br.com.dazzi.pokemon.adapter.`in`.rest.dto.PokemonResponse
import br.com.dazzi.pokemon.application.port.`in`.PokemonInputPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonInputPort: PokemonInputPort) {

    @GetMapping("/{num}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPokemonByNum(@PathVariable num: Int) : ResponseEntity<*> =
    LoggerFactory.getLogger(PokemonApplication::class.java)
        .let{


            var p = PokemonResponse(
                status = HttpStatus.OK.value(),
                message = null,
                result = pokemonInputPort.findPokemonByNum(num)
            )

            p
        }.let { ResponseEntity.ok(it) }


}