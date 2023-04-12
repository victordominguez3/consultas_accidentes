package mappers

import dto.AccidenteDto
import dto.AccidenteListDto
import models.Accidente
import `typealias`.ListaAccidentes

fun Accidente.toAccidenteDto() = AccidenteDto(
    numExp = numExp,
    fecha = fecha.toString(),
    hora = hora.toString(),
    localizacion = localizacion,
    numero = numero,
    codigoDistrito = codigoDistrito ?: 0,
    distrito = distrito,
    tipoAccidente = tipoAccidente.toString(),
    estadoMeteorologico = estadoMeteorologico ?: "",
    tipoVehiculo = tipoVehiculo,
    tipoPersona = tipoPersona.toString(),
    rangoEdad = rangoEdad,
    sexo = sexo.toString(),
    codLesividad = codLesividad ?: 0,
    lesividad = lesividad ?: "",
    coordX = coordX ?: 0.0,
    coordY = coordY ?: 0.0,
    positivoAlcohol = positivoAlcohol ?: false,
    positivoDroga = positivoDroga ?: false
)

fun ListaAccidentes.toAccidenteListDto() = AccidenteListDto(
    accidentes = map { it.toAccidenteDto() }
)