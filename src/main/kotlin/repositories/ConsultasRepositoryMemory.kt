package repositories

import enums.Sexo
import enums.TipoAccidente
import formatters.*
import mappers.*
import models.Accidente
import `typealias`.ListaAccidentes
import java.time.DayOfWeek
import java.time.Month

class ConsultasRepositoryMemory(): ConsultasRepository {

    private var accidentes = mutableListOf<Accidente>()

    override fun exportar(): ListaAccidentes {
        return accidentes
    }

    override fun importar(list: ListaAccidentes) {
        accidentes.clear()
        accidentes = list.toMutableList()
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
        accidentes.add(item)
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
                accidentes.removeAt(i)
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
}