package br.com.dazzi.pokemon.adapter.`in`.rest.handler

import br.com.dazzi.pokemon.adapter.`in`.rest.dto.PokemonResponse
import feign.FeignException
import feign.FeignException.NotFound
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(value = [Throwable::class])
    fun default(throwable: Throwable, request: WebRequest): ResponseEntity<*>{
        return HttpStatus.BAD_REQUEST.let {
            ResponseEntity(
                PokemonResponse(
                    status = it.value(),
                    message = "Something wrong is not right!!!",
                    result = null
                ),
                it
            )
        }

    }

    @ExceptionHandler(value = [NotFound::class])
    fun feignExceptionNotFound(throwable: Throwable, request: WebRequest): ResponseEntity<*>{
        return with((request as ServletWebRequest).request) {
            HttpStatus.NOT_FOUND.let {
                ResponseEntity(
                    PokemonResponse(
                        status = it.value(),
                        message = " Couldn't not found: ${this.requestURI}",
                        result = null
                    ),
                    it
                )
            }
        }

    }

}