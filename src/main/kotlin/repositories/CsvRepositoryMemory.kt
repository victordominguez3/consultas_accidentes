package repositories

import enums.Sexo
import enums.TipoAccidente
import formatters.*
import models.Accidente
import java.io.File
import java.time.DayOfWeek
import java.time.Month

class CsvRepositoryMemory: CsvRepository<Accidente> {

    val accidentes = leerCSV()

    override fun leerCSV(): List<Accidente> {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.csv"
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

    override fun positivoAlcoholODrogas(): List<Accidente> {
        return accidentes.filter { it.positivoAlcohol == true || it.positivoDroga == true }
    }

    override fun numPositivoAlcoholYDrogas(): Int {
        return accidentes.count { it.positivoAlcohol == true && it.positivoDroga == true }
    }

    override fun agruparPorSexo(): Map<Sexo, List<Accidente>> {
        return accidentes.groupBy { it.sexo }
    }

    override fun numAccidentesPorMeses(): Map<Month, Int> {
        return accidentes.groupBy { it.fecha.month }
            .mapValues { it.value.size }
    }

    override fun agruparPorTipoVehiculo(): Map<String, List<Accidente>> {
        return accidentes.groupBy { it.tipoVehiculo }
    }

    override fun enCalleLeganes(): List<Accidente> {
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

    override fun porDistritoDescendentemente(): Map<String, List<Accidente>> {
        return accidentes.sortedByDescending { it.distrito }
            .groupBy { it.distrito }
    }

    override fun accidentesFinSemanaNoche(): List<Accidente> {
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
    }

    override fun accidentesFinSemanaNochePositivoAlcohol(): List<Accidente> {
        return accidentes.filter { it.fecha.dayOfWeek == DayOfWeek.SATURDAY || it.fecha.dayOfWeek == DayOfWeek.SUNDAY }
            .filter { it.hora.hour in 0..6 }
            .filter { it.positivoAlcohol == true }
    }

    override fun accidentesUnoOMasDeUnFallecido(): List<Accidente> {
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

    override fun porMeteorologia(): Map<String?, List<Accidente>> {
        return accidentes.groupBy { it.estadoMeteorologico }
    }

    override fun accidentesAtropelloAnimal(): List<Accidente> {
        return accidentes.filter { it.tipoAccidente == TipoAccidente.AtropelloAnimal }

    }
}