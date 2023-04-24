package services.storage

import formatters.*
import models.Accidente
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

object CsvService: AccidenteStorageService {
    private val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"
    val fichero = File(path)
    override fun exportar(items: List<Accidente>) {
        logger.debug { "Exportando a CSV" }
        fichero.writeText("num_expediente;fecha;hora;localizacion;numero;cod_distrito;" +
                "distrito;tipo_accidente;estado_meteorológico;tipo_vehiculo;tipo_persona;" +
                "rango_edad;sexo;cod_lesividad;lesividad;coordenada_x_utm;coordenada_y_utm;positiva_alcohol;positiva_droga\n")
        items.forEach {
            fichero.appendText("${it.numExp};${it.fecha};${it.hora};${it.localizacion};${it.numero};${it.codigoDistrito};${it.distrito};${it.tipoAccidente}" +
                    ";${it.estadoMeteorologico};${it.tipoVehiculo};${it.tipoPersona};${it.rangoEdad};${it.sexo};${it.codLesividad};${it.lesividad};${it.coordX.toString()}" +
                    ";${it.coordY.toString()};${it.positivoAlcohol};${it.positivoDroga}\n")
        }
    }

    override fun importar(): List<Accidente> {
        logger.debug { "Importando desde CSV" }
        return fichero.readLines()
            .drop(1)
            .map { linea -> linea.split(";") }
            .map { columnas ->
                Accidente(
                    columnas[0],
                    columnas[1].toLocalDate(),
                    columnas[2].toLocalTime(),
                    columnas[3].removeSurrounding("\""),
                    columnas[4],
                    columnas[5].toIntOrNull(),
                    columnas[6],
                    columnas[7].toTipoAccidente(),
                    columnas[8],
                    columnas[9],
                    columnas[10].toTipoPersona(),
                    columnas[11],
                    columnas[12].toSexo(),
                    columnas[13].toIntOrNull(),
                    columnas[14],
                    columnas[15].removeSurrounding("\""),
                    columnas[16].removeSurrounding("\""),
                    columnas[17].toMyBoolean(),
                    columnas[18].toMyBoolean()
                )
            }
    }
}