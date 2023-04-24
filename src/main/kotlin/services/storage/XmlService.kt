package services.storage

import MORADO
import RESET
import mappers.toAccidenteList
import mappers.toAccidenteListDto
import models.Accidente
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File

private val logger = KotlinLogging.logger {}
object XmlService: AccidenteStorageService {

    private val path = "${System.getProperty("user.dir")}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentesXml.xml"
    private val fichero = File(path)
    private val serializer = Persister()

    override fun exportar(items: List<Accidente>) {
        logger.debug { "${MORADO}XmlService$RESET -> Exportando a XML" }
        serializer.write(items.toAccidenteListDto(), fichero)
    }

    override fun importar(): List<Accidente> {
        logger.debug { "${MORADO}XmlService$RESET -> Importando desde XML" }
        return serializer.read(dto.AccidenteListDto::class.java, fichero).toAccidenteList()
    }
}