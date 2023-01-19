# Pokemon Kt - Kotlin Hands-On para o time :ghost:


## Roteiro:


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

### Unit Test

```Kotlin
class PokemonResponseTest{


    @Test fun `should instantiate PokemonResponse correctly`(){
        val poke = PokemonResponse<PokemonDTO>(
            status = 888,
            message = "Something",
            result = null
        )

        poke.message shouldNotBe null
        poke.message shouldBe "Something"

        poke.result shouldBe null

        poke.status shouldBe 888
    }

}
```

- Service 
```Kotlin
internal class PokemonServiceTest {

    @MockK
    private lateinit var pokemonOutputPort : PokemonOutputPort

    private val pokemonService: PokemonService

    init {
        MockKAnnotations.init(this)
        pokemonService = PokemonService(pokemonOutputPort)
    }

    @Test
    fun `should find pokemon by ID correctly`() {
        every { pokemonOutputPort.findPokemonByNum(4) } returns
                PokemonDTO(
                    num = 4,
                    name = "charmander",
                    types = setOf("fire")
                )

        pokemonService.findPokemonByNum(4).let {
            it.name shouldBe "charmander"
            it.num shouldBe 4
            it.types shouldBe setOf("fire")
        }

        verify(exactly = 1){
            pokemonOutputPort.findPokemonByNum(withArg {
                it shouldBe 4
            })
        }

    }
}
```

- Mock Mvc
```Kotlin
@WebMvcTest
internal class PokemonControllerTest(
    @Autowired
    private val mvc: MockMvc
){

    @MockkBean
    private lateinit var service: PokemonService

    @Test fun `should perform a get call in app correctly`(){
        //{"status":200,"message":null,"result":{"num":4,"name":"charmander","types":["fire"]}}

        every { service.findPokemonByNum(4) } returns
                PokemonDTO(num = 4, name = "charmander", types = setOf("fire"))

        mvc.get("/pokemon/4")
            .andDo { MockMvcResultHandlers.print() }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("\$.status") { value(200)} }
            .andExpect { jsonPath("\$.message") { value(null)} }
            .andExpect { jsonPath("\$.result.num") { value(4)} }
            .andExpect { jsonPath("\$.result.name") { value("charmander")} }
            .andExpect { jsonPath("\$.result.types.[0]") { value("fire")} }

        verify(exactly = 1){
            service.findPokemonByNum(4)
        }
    }



}
```
