import com.squareup.moshi.Json
import controllers.ConsultasController
import enums.Sexo
import enums.TipoAccidente
import enums.TipoPersona
import models.Accidente
import repositories.ConsultasRepositoryMemory
import services.storage.CsvService
import services.storage.JsonService
import services.storage.XmlService
import java.time.LocalDate
import java.time.LocalTime

const val AZUL = "\u001B[36m"
const val MORADO = "\u001B[35m"
const val RESET = "\u001B[0m"

fun main(args: Array<String>) {

    val csvController = ConsultasController(ConsultasRepositoryMemory(), CsvService)
    val jsonController = ConsultasController(ConsultasRepositoryMemory(), JsonService)
    val xmlController = ConsultasController(ConsultasRepositoryMemory(), XmlService)

}