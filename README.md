# Pokemon Kt - Kotlin Hands-On para o time

---

## Roteiro:

---

- Application Data Class PokemonDTO
```Kotlin
data class PokemonDTO(
    val num: Int,
    val name: String,
    val types: Set<String>
)
```

- Extension Function - Mapper
```Kotlin
fun PokemonFeignDTO.toDTO(): PokemonDTO = PokemonDTO(
    num = this.num,
    name = this.name,
    types = this.types.map {
        it.type.name
    }.toMutableSet()
)
```

- Porta de Entrada
```Kotlin
interface PokemonInputPort {
    fun findPokemonByNum(num: Int): PokemonDTO
}
```

- Porta de Saída
```Kotlin
interface PokemonOutputPort {
    fun findPokemonByNum(num: Int): PokemonDTO
}
```

- Pokemon Service
```Kotlin
@Service
class PokemonService(
    private val pokemonOutputPort: PokemonOutputPort

) : PokemonInputPort {

    override fun findPokemonByNum(num: Int): PokemonDTO =
            pokemonOutputPort.findPokemonByNum(num)

}
```

- Pokemon Response
```Kotlin
data class PokemonResponse<O>(
    val status: Int,
    val message: String?,
    val result: O?
)
```

- Controller
```Kotlin
@RestController
@RequestMapping("/pokemon")
class PokemonController(
    private val pokemonInputPort: PokemonInputPort
) {

    @GetMapping("/{num}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPokemonByNum(@PathVariable num: Int) : ResponseEntity<*> =
        PokemonResponse(
            status = HttpStatus.OK.value(),
            message = null,
            result = pokemonInputPort.findPokemonByNum(num)
        ).let { ResponseEntity.ok(it) }

}
```