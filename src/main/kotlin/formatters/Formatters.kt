package formatters

import enums.Sexo
import enums.TipoAccidente
import enums.TipoPersona
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun String.toTipoPersona(): TipoPersona {
    return when(this) {
        "Conductor" -> TipoPersona.Conductor
        "Peatón" -> TipoPersona.Peaton
        "Testigo" -> TipoPersona.Testigo
        else -> TipoPersona.Viajero
    }
}

fun String.toSexo(): Sexo {
    return when(this) {
        "Hombre" -> Sexo.Hombre
        "Mujer" -> Sexo.Mujer
        else -> Sexo.Desconocido
    }
}

fun String.toMyBoolean(): Boolean? {
    return when(this) {
        "true" -> true
        "false" -> false
        else -> null
    }
}

fun String.toTipoAccidente(): TipoAccidente {
    return when(this) {
        "Colisión fronto-lateral" -> TipoAccidente.ColisionFrontoLateral
        "Colisión frontal" -> TipoAccidente.ColisionFrontal
        "Atropello a persona" -> TipoAccidente.AtropelloPersona
        "Choque contra obstáculo fijo" -> TipoAccidente.ChoqueObstaculo
        "Alcance" -> TipoAccidente.Alcance
        "Colisión lateral" -> TipoAccidente.ColisionLateral
        "Caída" -> TipoAccidente.Caida
        "Colisión múltiple" -> TipoAccidente.ColisionMultiple
        "Solo salida de la vía" -> TipoAccidente.SalidaVia
        "Atropello a animal" -> TipoAccidente.AtropelloAnimal
        "Vuelco" -> TipoAccidente.Vuelco
        "Despeñamiento" -> TipoAccidente.Despenyamiento
        else -> TipoAccidente.Otro
    }
}

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    return LocalDate.parse(this, formatter)
}

fun String.toLocalTime(): LocalTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
//    var hora = "0"
//    if (this.length == 7) {
//        hora += this
//        return LocalTime.parse(hora, formatter)
//    }
    return LocalTime.parse(this, formatter)
}