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

    val repository = ConsultasRepositoryMemory(CsvService)
    val controlador = ConsultasController(repository)

    val lista = controlador.buscarTodos()

    val repository2 = ConsultasRepositoryMemory(XmlService)
    val controlador2 = ConsultasController(repository2)

    lista.forEach { controlador2.guardar(it) }

//    controlador.guardar(
//        Accidente(
//            "caracol",
//            LocalDate.now(),
//            LocalTime.now(),
//            "",
//            "",
//            1,
//            "",
//            TipoAccidente.Alcance,
//            "",
//            "",
//            TipoPersona.Testigo,
//            "",
//            Sexo.Desconocido,
//            2,
//            "",
//            1.2,
//            2.2,
//            true,
//            false
//        )
//    )
//    controlador.buscarTodos().forEach { println(it) }
//    controlador.eliminarPorId("caracol")
//    controlador.buscarTodos().forEach { println(it) }
}