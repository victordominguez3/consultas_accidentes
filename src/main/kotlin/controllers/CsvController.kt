package controllers

import enums.Sexo
import models.Accidente
import repositories.CsvRepository
import java.time.Month

class CsvController(private val repository: CsvRepository<Accidente>) {

    fun leerCSV(): List<Accidente> {
        return repository.leerCSV()
    }

    fun positivoAlcoholODrogas(): List<Accidente> {
        return repository.positivoAlcoholODrogas()
    }

    fun numPositivoAlcoholYDrogas(): Int {
        return repository.numPositivoAlcoholYDrogas()
    }

    fun agruparPorSexo(): Map<Sexo, List<Accidente>> {
        return repository.agruparPorSexo()
    }

    fun numAccidentesPorMeses(): Map<Month, Int> {
        return repository.numAccidentesPorMeses()
    }

    fun agruparPorTipoVehiculo(): Map<String, List<Accidente>> {
        return repository.agruparPorTipoVehiculo()
    }

    fun enCalleLeganes(): List<Accidente> {
        return repository.enCalleLeganes()
    }

    fun numAccidentesPorDistrito(): Map<String, Int> {
        return repository.numAccidentesPorDistrito()
    }

    fun distritoMasAccidentes(): String {
        return repository.distritoMasAccidentes()
    }

    fun distritoMenosAccidentes(): String {
        return repository.distritoMenosAccidentes()
    }

    fun porDistritoDescendentemente(): Map<String, List<Accidente>> {
        return repository.porDistritoDescendentemente()
    }

    fun accidentesFinSemanaNoche(): List<Accidente> {
        return repository.accidentesFinSemanaNoche()
    }

    fun accidentesFinSemanaNochePositivoAlcohol(): List<Accidente> {
        return repository.accidentesFinSemanaNochePositivoAlcohol()
    }

    fun accidentesUnoOMasDeUnFallecido(): List<Accidente> {
        return repository.accidentesUnoOMasDeUnFallecido()
    }

    fun distritoMasAccidentesFinSemana(): String {
        return repository.distritoMasAccidentesFinSemana()
    }

    fun numAccidentesAlcoholODrogasYMortal(): Int {
        return repository.numAccidentesAlcoholODrogasYMortal()
    }

    fun numAccidentesAtropelloPersona(): Int {
        return repository.numAccidentesAtropelloPersona()
    }

    fun porMeteorologia(): Map<String?, List<Accidente>> {
        return repository.porMeteorologia()
    }

    fun accidentesAtropelloAnimal(): List<Accidente> {
        return repository.accidentesAtropelloAnimal()
    }
}