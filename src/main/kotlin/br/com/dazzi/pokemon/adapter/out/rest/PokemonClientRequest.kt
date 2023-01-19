package br.com.dazzi.pokemon.adapter.out.rest

import br.com.dazzi.pokemon.adapter.out.rest.feign.PokeApiRequest
import br.com.dazzi.pokemon.adapter.out.rest.mapper.toDTO
import br.com.dazzi.pokemon.application.dto.PokemonDTO
import br.com.dazzi.pokemon.application.port.out.PokemonOutputPort
import org.springframework.stereotype.Component

@Component
class PokemonClientRequest(
    private val pokeApiRequest: PokeApiRequest

) :  PokemonOutputPort{

    override fun findPokemonByNum(num: Int): PokemonDTO =
        pokeApiRequest.findPokemonByNum(num)
            .toDTO()

}