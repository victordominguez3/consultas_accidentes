package repositories

import enums.Sexo
import java.io.File
import java.time.Month

interface CsvRepository<T> {
    fun leerCSV(): List<T>
    fun positivoAlcoholODrogas(): List<T>
    fun numPositivoAlcoholYDrogas(): Int
    fun agruparPorSexo(): Map<Sexo, List<T>>
    fun numAccidentesPorMeses(): Map<Month, Int>
    fun agruparPorTipoVehiculo(): Map<String, List<T>>
    fun enCalleLeganes(): List<T>
    fun numAccidentesPorDistrito(): Map<String, Int>
    fun distritoMasAccidentes(): String
    fun distritoMenosAccidentes(): String
    fun porDistritoDescendentemente(): Map<String, List<T>>
    fun accidentesFinSemanaNoche(): List<T>
    fun accidentesFinSemanaNochePositivoAlcohol(): List<T>
    fun accidentesUnoOMasDeUnFallecido(): List<T>
    fun distritoMasAccidentesFinSemana(): String
    fun numAccidentesAlcoholODrogasYMortal(): Int
    fun numAccidentesAtropelloPersona(): Int
    fun porMeteorologia(): Map<String?, List<T>>
    fun accidentesAtropelloAnimal(): List<T>
    fun escribirJson(): File
    fun escribirXml(): File

}