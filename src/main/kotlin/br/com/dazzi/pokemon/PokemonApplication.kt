package br.com.dazzi.pokemon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PokemonApplication

fun main(args: Array<String>) {
	runApplication<PokemonApplication>(*args)
}
