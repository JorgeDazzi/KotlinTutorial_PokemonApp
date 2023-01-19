package br.com.dazzi.pokemon.adapter.out.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonFeignDTO(
    @JsonProperty("id")
    val num: Int,
    val name: String,
    val types: Set<Types>
)

data class Types(
    val type: Element
)

data class Element(
    val name: String
)