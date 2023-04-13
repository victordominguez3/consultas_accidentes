package services.storage

import mappers.toAccidenteList
import mappers.toAccidenteListDto
import models.Accidente
import org.simpleframework.xml.core.Persister
import java.io.File

object XmlService: AccidenteStorageService {

    private val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentesXml.xml"
    private val fichero = File(path)
    private val serializer = Persister()

    override fun saveAll(items: List<Accidente>) {
        serializer.write(items.toAccidenteListDto(), fichero)
    }

    override fun loadAll(): List<Accidente> {
        return serializer.read(dto.AccidenteListDto::class.java, fichero).toAccidenteList()
    }
}