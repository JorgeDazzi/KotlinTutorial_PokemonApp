package br.com.dazzi.pokemon.application.dto

data class PokemonDTO(
    val num: Int,
    val name: String,
    val types: Set<String>
)
