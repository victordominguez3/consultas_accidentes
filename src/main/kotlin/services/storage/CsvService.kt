package services.storage

import formatters.*
import models.Accidente
import java.io.File

object CsvService: AccidenteStorageService {
    override fun guardarTodos(items: List<Accidente>) {

    }

    override fun cargarTodos(): List<Accidente> {
        val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"
        val fichero = File(path)

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
                    columnas[15].removeSurrounding("\"").replace(",", ".").toDoubleOrNull(),
                    columnas[16].removeSurrounding("\"").replace(",", ".").toDoubleOrNull(),
                    columnas[17].toMyBoolean(),
                    columnas[18].toMyBoolean()
                )
            }    }
}