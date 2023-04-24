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
        "1.2",
        "2.2",
        true,
        false
    )

    val csvController = ConsultasController(ConsultasRepositoryMemory(), CsvService)
    val jsonController = ConsultasController(ConsultasRepositoryMemory(), JsonService)
    val xmlController = ConsultasController(ConsultasRepositoryMemory(), XmlService)

    xmlController.guardar(accidente)
    xmlController.exportar()
    readln()
    xmlController.eliminarPorId("caracol")
    xmlController.exportar()
//    csvController.importar()
//    val lista = csvController.buscarTodos()
//    csvController.guardar(accidente)
//    jsonController.guardar(accidente)
//    xmlController.guardar(accidente)
//readln()
//    csvController.eliminarPorId("caracol")
//    jsonController.eliminarPorId("caracol")
//    xmlController.eliminarPorId("caracol")
}