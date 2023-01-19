package br.com.dazzi.pokemon.application.port.`in`

import br.com.dazzi.pokemon.application.dto.PokemonDTO

interface PokemonInputPort {
    fun findPokemonByNum(num: Int): PokemonDTO
}