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

const val MORADO = "\u001B[35m"
const val RESET = "\u001B[0m"

fun main(args: Array<String>) {

    val accidente = Accidente(
        "caracol",
        LocalDate.now(),
        LocalTime.now(),
        "",
        "",
        1,
        "",
        TipoAccidente.Alcance,
        "",
        "",
        TipoPersona.Testigo,
        "",
        Sexo.Desconocido,
        2,
        "",
        1.2.toString(),
        2.2.toString(),
        true,
        false
    )

    val csvController = ConsultasController(ConsultasRepositoryMemory(), CsvService)
    val jsonController = ConsultasController(ConsultasRepositoryMemory(), JsonService)

    csvController.importar()
    val lista = csvController.buscarTodos()
    for (i in lista) {
        jsonController.guardar(i)
    }
    jsonController.buscarTodos().forEach { println(it) }
    jsonController.exportar()

}