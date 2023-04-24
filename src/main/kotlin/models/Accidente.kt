package models

import AZUL
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
    val coordX: String?,
    val coordY: String?,
    val positivoAlcohol: Boolean?,
    val positivoDroga: Boolean?
) {
    override fun toString(): String {
        return "${AZUL}Num. Exp$RESET: $numExp,\t" +
                "${AZUL}Fecha$RESET: $fecha,\t" +
                "${AZUL}Hora$RESET: $hora,\t" +
                "${AZUL}Localizacion$RESET: $localizacion,\t" +
                "${AZUL}Número$RESET: $numero,\t" +
                "${AZUL}Código Distrito$RESET: $codigoDistrito,\t" +
                "${AZUL}Distrito$RESET: $distrito,\t" +
                "${AZUL}Tipo models.Accidente$RESET: $tipoAccidente,\t" +
                "${AZUL}Estado Meteorológico$RESET: $estadoMeteorologico,\t" +
                "${AZUL}Tipo Vehiculo$RESET: $tipoVehiculo,\t" +
                "${AZUL}Tipo Persona$RESET: $tipoPersona,\t" +
                "${AZUL}Rango Edad$RESET: $rangoEdad,\t" +
                "${AZUL}enums.Sexo$RESET: $sexo,\t" +
                "${AZUL}Código Lesivilidad$RESET: $codLesividad,\t" +
                "${AZUL}Lesivilidad$RESET: $lesividad,\t" +
                "${AZUL}Coordeanda X$RESET: $coordX,\t" +
                "${AZUL}Coordenada Y$RESET: $coordY,\t" +
                "${AZUL}Positivo Alochol$RESET: $positivoAlcohol,\t" +
                "${AZUL}Positivo Droga$RESET: $positivoDroga"
    }
}