package exceptions

import java.lang.Exception

sealed class AccidenteException(message: String): Exception(message)
class AccidenteNoEncontradoException(message: String): AccidenteException("Accidente no encontrado: $message")
class AccidenteNoValidoException(message: String): AccidenteException("Accidente no válido: $message")