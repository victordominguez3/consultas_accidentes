import controllers.CsvController
import enums.Sexo
import enums.TipoAccidente
import enums.TipoPersona
import models.Accidente
import repositories.ConsultasRepositoryMemory
import services.storage.AccidenteStorageService
import services.storage.StorageService
import services.storage.XmlService
import java.time.LocalDate
import java.time.LocalTime

const val MORADO = "\u001B[35m"
const val RESET = "\u001B[0m"

fun main(args: Array<String>) {

    val storage = XmlService
    val repository = ConsultasRepositoryMemory(storage)
    val controlador = CsvController(repository)

    controlador.buscarTodos()
    controlador.guardar(
        Accidente(
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
            1.2,
            2.2,
            true,
            false
        )
    )
}