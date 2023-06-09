package services.storage

import MORADO
import RESET
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Accidente
import mu.KotlinLogging
import `typealias`.ListaAccidentes
import utils.LocalDateAdapter
import utils.LocalTimeAdapter
import utils.toPrettyJson
import java.io.File
import kotlin.math.log

private val logger = KotlinLogging.logger {}

@OptIn(ExperimentalStdlibApi::class)
object JsonService: AccidenteStorageService {

    private val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentesJson.json"

    private val moshi = Moshi.Builder()
        .add(LocalDateAdapter())
        .add(LocalTimeAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<ListaAccidentes>()

    override fun exportar(items: ListaAccidentes) {
        logger.debug { "${MORADO}JsonService$RESET -> Exportando a JSON" }
        val fichero = File(path)
        fichero.writeText(jsonAdapter.toPrettyJson(items))
    }

    override fun importar(): ListaAccidentes {
        logger.debug { "${MORADO}JsonService$RESET -> Importando desde JSON" }
        val fichero = File(path)
        return jsonAdapter.fromJson(fichero.readText()) ?: emptyList()
    }
}