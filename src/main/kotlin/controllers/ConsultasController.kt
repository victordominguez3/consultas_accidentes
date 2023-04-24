package controllers

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import enums.Sexo
import exceptions.AccidenteException
import exceptions.AccidenteNoEncontradoException
import exceptions.AccidenteNoValidoException
import models.Accidente
import repositories.ConsultasRepository
import services.storage.AccidenteStorageService
import `typealias`.ListaAccidentes
import java.time.Month

class ConsultasController(
    private val repository: ConsultasRepository,
    private val storage: AccidenteStorageService
) {

    init {
        importar()
    }

    fun importar() {
        repository.importar(storage.importar())
    }

    fun exportar() {
        storage.exportar(repository.exportar())
    }

    fun buscarTodos(): ListaAccidentes {
        return repository.buscarTodos()
    }

    fun buscarPorId(id: String): Result<Accidente, AccidenteException> {
        return repository.buscarPorId(id)?.let { Ok(it) }
            ?: Err(AccidenteNoEncontradoException("No se ha encontrado ningún accidente con el id proporcionado"))
    }

    fun guardar(item: Accidente): Result<Accidente, AccidenteException> {
        return repository.guardar(item)?.let { Ok(it) }
            ?: Err(AccidenteNoValidoException("Ya existe un accidente con el mismo número de expediente"))
    }

    fun actualizar(item: Accidente): Result<ListaAccidentes, AccidenteException> {
        return repository.actualizar(item)?.let { Ok(it) }
            ?: Err(AccidenteNoEncontradoException("No se pudo actualizar el accidente porque no existe en la lista"))
    }

    fun eliminarPorId(id: String): Result<ListaAccidentes, AccidenteException> {
        return repository.eliminarPorId(id)?.let { Ok(it) }
            ?: Err(AccidenteNoEncontradoException("No existe ningún accidente con ese número de expediente"))
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