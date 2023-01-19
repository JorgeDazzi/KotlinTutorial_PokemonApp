package br.com.dazzi.pokemon.adapter.out.rest.feign

import br.com.dazzi.pokemon.adapter.out.rest.dto.PokemonFeignDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "pokeapi", url = "https://pokeapi.co/api/v2/")
interface PokeApiRequest {

    @RequestMapping(method = [RequestMethod.GET], value = ["pokemon/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPokemonByNum(@PathVariable id: Int): PokemonFeignDTO

}