package br.com.dazzi.pokemon.adapter.`in`.rest.dto

data class PokemonResponse<O>(
    val status: Int,
    val message: String?,
    val result: O?
)
