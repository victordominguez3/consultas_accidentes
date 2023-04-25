package controllers

import MORADO
import RESET
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import enums.Sexo
import exceptions.AccidenteException
import exceptions.AccidenteNoEncontradoException
import exceptions.AccidenteNoValidoException
import models.Accidente
import mu.KotlinLogging
import repositories.ConsultasRepository
import services.storage.AccidenteStorageService
import `typealias`.ListaAccidentes
import validators.validar
import java.time.Month

private val logger = KotlinLogging.logger {}

class ConsultasController(
    private val repository: ConsultasRepository,
    private val storage: AccidenteStorageService
) {

    init {
        logger.debug { "${MORADO}Controller$RESET -> Importación de datos inicial" }
        importar()
    }

    fun importar() {
        logger.debug { "${MORADO}Controller$RESET -> Importar al repositorio desde storage" }
        repository.importar(storage.importar())
    }

    fun exportar() {
        logger.debug { "${MORADO}Controller$RESET -> Exportar a storage desde el repositorio" }
        storage.exportar(repository.exportar())
    }

    fun buscarTodos(): ListaAccidentes {
        logger.debug { "${MORADO}Controller$RESET -> Buscar todos" }
        return repository.buscarTodos()
    }

    fun buscarPorId(id: String): Result<Accidente, AccidenteException> {
        logger.debug { "${MORADO}Controller$RESET -> Buscar por id: $id" }
        return repository.buscarPorId(id)?.let { Ok(it) }
            ?: Err(AccidenteNoEncontradoException("No se ha encontrado ningún accidente con el id proporcionado"))
    }

    fun guardar(item: Accidente): Result<Accidente, AccidenteException> {
        logger.debug { "${MORADO}Controller$RESET -> Guardar: $item" }

        return repository.guardar(item).also { item.validar() }?.let { Ok(it) }
            ?: Err(AccidenteNoValidoException("Ya existe un accidente con el mismo número de expediente"))
    }

    fun actualizar(item: Accidente): Result<ListaAccidentes, AccidenteException> {
        logger.debug { "${MORADO}Controller$RESET -> Actualizar: $item" }
        return repository.actualizar(item).also { item.validar() }?.let { Ok(it) }
            ?: Err(AccidenteNoEncontradoException("No se pudo actualizar el accidente porque no existe en la lista"))
    }

    fun eliminarPorId(id: String): Result<ListaAccidentes, AccidenteException> {
        logger.debug { "${MORADO}Controller$RESET -> Eliminar por id: $id" }
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