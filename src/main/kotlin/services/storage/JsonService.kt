package services.storage

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Accidente
import `typealias`.ListaAccidentes
import utils.LocalDateAdapter
import utils.LocalTimeAdapter
import utils.toPrettyJson
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
object JsonService: AccidenteStorageService {

    private val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentesJson.json"

    private val moshi = Moshi.Builder()
        .add(LocalDateAdapter())
        .add(LocalTimeAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<ListaAccidentes>()

    override fun saveAll(items: List<Accidente>) {
        val fichero = File(path)
        fichero.writeText(jsonAdapter.toPrettyJson(items))
    }

    override fun loadAll(): List<Accidente> {
        val fichero = File(path)
        return jsonAdapter.fromJson(fichero.readText()) ?: emptyList()
    }
}