package services.storage

import mappers.toAccidenteList
import mappers.toAccidenteListDto
import models.Accidente
import org.simpleframework.xml.core.Persister
import java.io.File

object XmlService: AccidenteStorageService {

    private val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentesXml.xml"
    private val fichero = File(path)
    private val serializer = Persister()

    override fun exportar(items: List<Accidente>) {
        serializer.write(items.toAccidenteListDto(), fichero)
    }

    override fun importar(): List<Accidente> {
        return serializer.read(dto.AccidenteListDto::class.java, fichero).toAccidenteList()
    }
}