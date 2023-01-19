package br.com.dazzi.pokemon.adapter.out.rest.mapper

import br.com.dazzi.pokemon.adapter.out.rest.dto.PokemonFeignDTO
import br.com.dazzi.pokemon.application.dto.PokemonDTO

fun PokemonFeignDTO.toDTO(): PokemonDTO = PokemonDTO(
    num = this.num,
    name = this.name,
    types = this.types.map {
        it.type.name
    }.toMutableSet()
)