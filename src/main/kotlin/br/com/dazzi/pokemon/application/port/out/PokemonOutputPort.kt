package br.com.dazzi.pokemon.application.port.out

import br.com.dazzi.pokemon.application.dto.PokemonDTO

interface PokemonOutputPort {
    fun findPokemonByNum(num: Int): PokemonDTO
}