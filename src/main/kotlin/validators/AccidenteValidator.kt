package validators

import exceptions.AccidenteNoValidoException
import models.Accidente
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun Accidente.validar() {
    logger.debug { "Validando: $this" }
    require(numExp.isNotBlank()) { throw AccidenteNoValidoException("Es necesario un número de expediente") }
    require(localizacion.isNotBlank()) { throw AccidenteNoValidoException("Es necesaria una localización") }
}