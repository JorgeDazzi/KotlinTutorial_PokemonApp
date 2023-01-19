package br.com.dazzi.pokemon.application.service

import br.com.dazzi.pokemon.application.dto.PokemonDTO
import br.com.dazzi.pokemon.application.port.`in`.PokemonInputPort
import br.com.dazzi.pokemon.application.port.out.PokemonOutputPort
import org.springframework.stereotype.Service

@Service
class PokemonService(private val pokemonOutputPort: PokemonOutputPort) : PokemonInputPort {

    override fun findPokemonByNum(num: Int): PokemonDTO =
            pokemonOutputPort.findPokemonByNum(num)

}