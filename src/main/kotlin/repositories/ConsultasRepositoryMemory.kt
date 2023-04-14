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
import services.storage.AccidenteStorageService
import `typealias`.ListaAccidentes
import utils.LocalDateAdapter
import utils.LocalTimeAdapter
import utils.toPrettyJson
import java.io.File
import java.lang.Exception
import java.time.DayOfWeek
import java.time.Month

class ConsultasRepositoryMemory(private val storageService: AccidenteStorageService): ConsultasRepository {

    private var accidentes = mutableListOf<Accidente>()

    private fun saveAll() {
        storageService.saveAll(accidentes)
    }

    private fun loadAll() {
        accidentes.clear()
        accidentes = storageService.loadAll().toMutableList()
    }

    override fun buscarTodos(): ListaAccidentes {
        loadAll()
        return accidentes
    }

    override fun buscarPorId(id: String): Accidente? {
        loadAll()
        return accidentes.find { it.numExp == id }
    }

    override fun guardar(item: Accidente): Accidente? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == item.numExp) {
                return null
            }
        }
        accidentes.toMutableList().add(item)
        saveAll()
        return item
    }

    override fun actualizar(item: Accidente): ListaAccidentes? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == item.numExp) {
                accidentes.toMutableList()[i] = item
                saveAll()
                return accidentes
            }
        }
        return null
    }

    override fun eliminarPorId(id: String): ListaAccidentes? {
        for (i in accidentes.indices) {
            if (accidentes[i].numExp == id) {
                accidentes.drop(i)
                saveAll()
                return accidentes
            }
        }
        return null
    }

    override fun positivoAlcoholODrogas(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.positivoAlcohol == true || it.positivoDroga == true }
    }

    override fun numPositivoAlcoholYDrogas(): Int {
        loadAll()
        return accidentes.count { it.positivoAlcohol == true && it.positivoDroga == true }
    }

    override fun agruparPorSexo(): Map<Sexo, ListaAccidentes> {
        loadAll()
        return accidentes.groupBy { it.sexo }
    }

    override fun numAccidentesPorMeses(): Map<Month, Int> {
        loadAll()
        return accidentes.groupBy { it.fecha.month }
            .mapValues { it.value.size }
    }

    override fun agruparPorTipoVehiculo(): Map<String, ListaAccidentes> {
        loadAll()
        return accidentes.groupBy { it.tipoVehiculo }
    }

    override fun enCalleLeganes(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.localizacion.contains("LEGANES") }
    }

    override fun numAccidentesPorDistrito(): Map<String, Int> {
        loadAll()
        return accidentes.groupBy { it.distrito }
            .mapValues { it.value.size }
    }

    override fun distritoMasAccidentes(): String {
        loadAll()
        return accidentes.groupBy { it.distrito }
            .maxBy { it.value.size }
            .key
    }

    override fun distritoMenosAccidentes(): String {
        loadAll()
        return accidentes.groupBy { it.distrito }
            .minBy { it.value.size }
            .key
    }

    override fun porDistritoDescendentemente(): Map<String, ListaAccidentes> {
        loadAll()
        return accidentes.sortedByDescending { it.distrito }
            .groupBy { it.distrito }
    }

    override fun accidentesFinSemanaNoche(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
    }

    override fun accidentesFinSemanaNochePositivoAlcohol(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
            .filter { it.positivoAlcohol == true }
    }

    override fun accidentesUnoOMasDeUnFallecido(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.codLesividad == 4 }
    }

    override fun distritoMasAccidentesFinSemana(): String {
        loadAll()
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .groupBy { it.distrito }
            .maxBy { it.value.size }
            .key
    }

    override fun numAccidentesAlcoholODrogasYMortal(): Int {
        loadAll()
        return accidentes.filter { it.positivoAlcohol == true || it.positivoDroga == true }
            .filter { it.codLesividad == 4 }.size
    }

    override fun numAccidentesAtropelloPersona(): Int {
        loadAll()
        return accidentes.filter { it.tipoAccidente == TipoAccidente.AtropelloPersona }.size
    }

    override fun porMeteorologia(): Map<String?, ListaAccidentes> {
        loadAll()
        return accidentes.groupBy { it.estadoMeteorologico }
    }

    override fun accidentesAtropelloAnimal(): ListaAccidentes {
        loadAll()
        return accidentes.filter { it.tipoAccidente == TipoAccidente.AtropelloAnimal }

    }
}