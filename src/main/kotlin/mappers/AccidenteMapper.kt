package mappers

import dto.AccidenteDto
import dto.AccidenteListDto
import formatters.*
import models.Accidente
import `typealias`.ListaAccidentes

fun Accidente.toAccidenteDto() = AccidenteDto(
    numExp = numExp,
    fecha = fecha.toString(),
    hora = hora.toString(),
    localizacion = localizacion,
    numero = numero,
    codigoDistrito = codigoDistrito.toString(),
    distrito = distrito,
    tipoAccidente = tipoAccidente.toString(),
    estadoMeteorologico = estadoMeteorologico ?: "",
    tipoVehiculo = tipoVehiculo,
    tipoPersona = tipoPersona.toString(),
    rangoEdad = rangoEdad,
    sexo = sexo.toString(),
    codLesividad = codLesividad ?: 0,
    lesividad = lesividad ?: "",
    coordX = coordX,
    coordY = coordY,
    positivoAlcohol = positivoAlcohol ?: false,
    positivoDroga = positivoDroga ?: false
)

fun ListaAccidentes.toAccidenteListDto() = AccidenteListDto(
    accidentes = map { it.toAccidenteDto() }
)

fun AccidenteDto.toAccidente() = Accidente(
    numExp = numExp,
    fecha = fecha.toLocalDate(),
    hora = hora.toLocalTime(),
    localizacion = localizacion,
    numero = numero,
    codigoDistrito = codigoDistrito?.toInt(),
    distrito = distrito,
    tipoAccidente = tipoAccidente.toTipoAccidente(),
    estadoMeteorologico = estadoMeteorologico,
    tipoVehiculo = tipoVehiculo,
    tipoPersona = tipoPersona.toTipoPersona(),
    rangoEdad = rangoEdad,
    sexo = sexo.toSexo(),
    codLesividad = codLesividad,
    lesividad = lesividad,
    coordX = coordX,
    coordY = coordY,
    positivoAlcohol = positivoAlcohol,
    positivoDroga = positivoDroga
)

fun AccidenteListDto.toAccidenteList() = accidentes.map { it.toAccidente() }