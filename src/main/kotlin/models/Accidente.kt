package models

import MORADO
import RESET
import enums.Sexo
import enums.TipoAccidente
import enums.TipoPersona
import java.time.LocalDate
import java.time.LocalTime

class Accidente(
    val numExp: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val localizacion: String,
    val numero: String,
    val codigoDistrito: Int?,
    val distrito: String,
    val tipoAccidente: TipoAccidente,
    val estadoMeteorologico: String?,
    val tipoVehiculo: String,
    val tipoPersona: TipoPersona,
    val rangoEdad: String,
    val sexo: Sexo,
    val codLesividad: Int?,
    val lesividad: String?,
    val coordX: Double?,
    val coordY: Double?,
    val positivoAlcohol: Boolean?,
    val positivoDroga: Boolean?
) {
    override fun toString(): String {
        return "${MORADO}Num. Exp$RESET: $numExp,\t" +
                "${MORADO}Fecha$RESET: $fecha,\t" +
                "${MORADO}Hora$RESET: $hora,\t" +
                "${MORADO}Localizacion$RESET: $localizacion,\t" +
                "${MORADO}Número$RESET: $numero,\t" +
                "${MORADO}Código Distrito$RESET: $codigoDistrito,\t" +
                "${MORADO}Distrito$RESET: $distrito,\t" +
                "${MORADO}Tipo models.Accidente$RESET: $tipoAccidente,\t" +
                "${MORADO}Estado Meteorológico$RESET: $estadoMeteorologico,\t" +
                "${MORADO}Tipo Vehiculo$RESET: $tipoVehiculo,\t" +
                "${MORADO}Tipo Persona$RESET: $tipoPersona,\t" +
                "${MORADO}Rango Edad$RESET: $rangoEdad,\t" +
                "${MORADO}enums.Sexo$RESET: $sexo,\t" +
                "${MORADO}Código Lesivilidad$RESET: $codLesividad,\t" +
                "${MORADO}Lesivilidad$RESET: $lesividad,\t" +
                "${MORADO}Coordeanda X$RESET: $coordX,\t" +
                "${MORADO}Coorenada Y$RESET: $coordY,\t" +
                "${MORADO}Positivo Alochol$RESET: $positivoAlcohol,\t" +
                "${MORADO}Positivo Droga$RESET: $positivoDroga"
    }
}