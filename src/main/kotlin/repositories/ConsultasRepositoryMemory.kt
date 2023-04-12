package repositories

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import enums.Sexo
import enums.TipoAccidente
import formatters.*
import mappers.*
import models.Accidente
import org.simpleframework.xml.core.Persister
import `typealias`.ListaAccidentes
import utils.LocalDateAdapter
import utils.LocalTimeAdapter
import utils.toPrettyJson
import java.io.File
import java.lang.Exception
import java.time.DayOfWeek
import java.time.Month

class ConsultasRepositoryMemory: ConsultasRepository {

    var accidentes = leerCSV()

    override fun leerCSV(): ListaAccidentes {
        val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"
        val fichero = File(path)

        return fichero.readLines()
            .drop(1)
            .map { linea -> linea.split(";") }
            .map { columnas ->
                Accidente(
                    columnas[0],
                    columnas[1].toLocalDate(),
                    columnas[2].toLocalTime(),
                    columnas[3].removeSurrounding("\""),
                    columnas[4],
                    columnas[5].toIntOrNull(),
                    columnas[6],
                    columnas[7].toTipoAccidente(),
                    columnas[8],
                    columnas[9],
                    columnas[10].toTipoPersona(),
                    columnas[11],
                    columnas[12].toSexo(),
                    columnas[13].toIntOrNull(),
                    columnas[14],
                    columnas[15].removeSurrounding("\"").replace(",", ".").toDoubleOrNull(),
                    columnas[16].removeSurrounding("\"").replace(",", ".").toDoubleOrNull(),
                    columnas[17].toMyBoolean(),
                    columnas[18].toMyBoolean()
                )
            }
    }

    override fun buscarTodos(): ListaAccidentes {
        return accidentes
    }

    override fun buscarPorId(id: String): Accidente? {
        return accidentes.find { it.numExp == id }
    }

    override fun guardar(item: Accidente): Accidente? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == item.numExp) {
                return null
            }
        }
        return item
    }

    override fun actualizar(item: Accidente): ListaAccidentes? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == item.numExp) {
                accidentes.toMutableList()[i] = item
                return accidentes
            }
        }
        return null
    }

    override fun eliminarPorId(id: String): ListaAccidentes? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == id) {
                accidentes.drop(i)
                return accidentes
            }
        }
        return null
    }

    override fun positivoAlcoholODrogas(): ListaAccidentes {
        return accidentes.filter { it.positivoAlcohol == true || it.positivoDroga == true }
    }

    override fun numPositivoAlcoholYDrogas(): Int {
        return accidentes.count { it.positivoAlcohol == true && it.positivoDroga == true }
    }

    override fun agruparPorSexo(): Map<Sexo, ListaAccidentes> {
        return accidentes.groupBy { it.sexo }
    }

    override fun numAccidentesPorMeses(): Map<Month, Int> {
        return accidentes.groupBy { it.fecha.month }
            .mapValues { it.value.size }
    }

    override fun agruparPorTipoVehiculo(): Map<String, ListaAccidentes> {
        return accidentes.groupBy { it.tipoVehiculo }
    }

    override fun enCalleLeganes(): ListaAccidentes {
        return accidentes.filter { it.localizacion.contains("LEGANES") }
    }

    override fun numAccidentesPorDistrito(): Map<String, Int> {
        return accidentes.groupBy { it.distrito }
            .mapValues { it.value.size }
    }

    override fun distritoMasAccidentes(): String {
        return accidentes.groupBy { it.distrito }
            .maxBy { it.value.size }
            .key
    }

    override fun distritoMenosAccidentes(): String {
        return accidentes.groupBy { it.distrito }
            .minBy { it.value.size }
            .key
    }

    override fun porDistritoDescendentemente(): Map<String, ListaAccidentes> {
        return accidentes.sortedByDescending { it.distrito }
            .groupBy { it.distrito }
    }

    override fun accidentesFinSemanaNoche(): ListaAccidentes {
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
    }

    override fun accidentesFinSemanaNochePositivoAlcohol(): ListaAccidentes {
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
            .filter { it.positivoAlcohol == true }
    }

    override fun accidentesUnoOMasDeUnFallecido(): ListaAccidentes {
        return accidentes.filter { it.codLesividad == 4 }
    }

    override fun distritoMasAccidentesFinSemana(): String {
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .groupBy { it.distrito }
            .maxBy { it.value.size }
            .key
    }

    override fun numAccidentesAlcoholODrogasYMortal(): Int {
        return accidentes.filter { it.positivoAlcohol == true || it.positivoDroga == true }
            .filter { it.codLesividad == 4 }.size
    }

    override fun numAccidentesAtropelloPersona(): Int {
        return accidentes.filter { it.tipoAccidente == TipoAccidente.AtropelloPersona }.size
    }

    override fun porMeteorologia(): Map<String?, ListaAccidentes> {
        return accidentes.groupBy { it.estadoMeteorologico }
    }

    override fun accidentesAtropelloAnimal(): ListaAccidentes {
        return accidentes.filter { it.tipoAccidente == TipoAccidente.AtropelloAnimal }

    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun escribirJson(): File {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentesJson.json"
        val fichero = File(path)

        val moshi = Moshi.Builder()
            .add(LocalDateAdapter())
            .add(LocalTimeAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter = moshi.adapter<ListaAccidentes>()

        fichero.writeText(jsonAdapter.toPrettyJson(accidentes))

        return fichero
    }

    override fun escribirXml(): File {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentesXml.xml"
        val fichero = File(path)

        val serializer = Persister()

        serializer.write(accidentes.toAccidenteListDto(), fichero)

        return fichero
    }
}