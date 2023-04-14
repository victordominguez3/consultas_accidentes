package controllers

import enums.Sexo
import exceptions.AccidenteNoEncontradoException
import exceptions.AccidenteNoValidoException
import models.Accidente
import repositories.ConsultasRepository
import `typealias`.ListaAccidentes
import java.io.File
import java.lang.Exception
import java.time.Month

class CsvController(private val repository: ConsultasRepository) {

    fun buscarTodos(): ListaAccidentes {
        return repository.buscarTodos()
    }

    fun buscarPorId(id: String): Accidente {
        return repository.buscarPorId(id)
            ?: throw AccidenteNoEncontradoException("No existe ningún accidente con ese número de expediente")
    }

    fun guardar(item: Accidente): Accidente {
        return repository.guardar(item)
            ?: throw AccidenteNoValidoException("Ya existe un accidente con el mismo número de expediente")
    }

    fun actualizar(item: Accidente): ListaAccidentes {
        return repository.actualizar(item)
            ?: throw AccidenteNoEncontradoException("No se pudo actualizar el accidente porque no existe en la lista")
    }

    fun eliminarPorId(id: String): ListaAccidentes {
        return repository.eliminarPorId(id)
            ?: throw AccidenteNoEncontradoException("No existe ningún accidente con ese número de expediente")
    }

    fun positivoAlcoholODrogas(): ListaAccidentes {
        return repository.positivoAlcoholODrogas()
    }

    fun numPositivoAlcoholYDrogas(): Int {
        return repository.numPositivoAlcoholYDrogas()
    }

    fun agruparPorSexo(): Map<Sexo, ListaAccidentes> {
        return repository.agruparPorSexo()
    }

    fun numAccidentesPorMeses(): Map<Month, Int> {
        return repository.numAccidentesPorMeses()
    }

    fun agruparPorTipoVehiculo(): Map<String, ListaAccidentes> {
        return repository.agruparPorTipoVehiculo()
    }

    fun enCalleLeganes(): ListaAccidentes {
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

    fun porDistritoDescendentemente(): Map<String, ListaAccidentes> {
        return repository.porDistritoDescendentemente()
    }

    fun accidentesFinSemanaNoche(): ListaAccidentes {
        return repository.accidentesFinSemanaNoche()
    }

    fun accidentesFinSemanaNochePositivoAlcohol(): ListaAccidentes {
        return repository.accidentesFinSemanaNochePositivoAlcohol()
    }

    fun accidentesUnoOMasDeUnFallecido(): ListaAccidentes {
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

    fun porMeteorologia(): Map<String?, ListaAccidentes> {
        return repository.porMeteorologia()
    }

    fun accidentesAtropelloAnimal(): ListaAccidentes {
        return repository.accidentesAtropelloAnimal()
    }
}