package dto

import models.Accidente
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "accidente")
class AccidenteDto(
    @field:Attribute(name = "numExpediente")
    @param:Attribute(name = "numExpediente")
    val numExp: String = "",

    @field:Attribute(name = "fecha")
    @param:Attribute(name = "fecha")
    val fecha: String = "",

    @field:Attribute(name = "hora")
    @param:Attribute(name = "hora")
    val hora: String = "",

    @field:Element(name = "localizacion")
    @param:Element(name = "localizacion")
    val localizacion: String = "",

    @field:Element(name = "numero")
    @param:Element(name = "numero")
    val numero: String = "",

    @field:Element(name = "codDistrito")
    @param:Element(name = "codDistrito")
    val codigoDistrito: Int? = 0,

    @field:Element(name = "distrito")
    @param:Element(name = "distrito")
    val distrito: String = "",

    @field:Element(name = "tipo")
    @param:Element(name = "tipo")
    val tipoAccidente: String = "",

    @field:Element(name = "estadoMeteorologico")
    @param:Element(name = "estadoMeteorologico")
    val estadoMeteorologico: String? = "",

    @field:Element(name = "tipoVehiculo")
    @param:Element(name = "tipoVehiculo")
    val tipoVehiculo: String = "",

    @field:Element(name = "tipoPersona")
    @param:Element(name = "tipoPersona")
    val tipoPersona: String = "",

    @field:Element(name = "edad")
    @param:Element(name = "edad")
    val rangoEdad: String = "",

    @field:Element(name = "sexo")
    @param:Element(name = "sexo")
    val sexo: String = "",

    @field:Element(name = "codLesividad")
    @param:Element(name = "codLesividad")
    val codLesividad: Int? = 0,

    @field:Element(name = "lesividad")
    @param:Element(name = "lesividad")
    val lesividad: String? = "",

    @field:Element(name = "coordenadaX")
    @param:Element(name = "coordenadaX")
    val coordX: Double? = 0.0,

    @field:Element(name = "coordenadaY")
    @param:Element(name = "coordenadaY")
    val coordY: Double? = 0.0,

    @field:Element(name = "positivoAlcohol")
    @param:Element(name = "positivoAlcohol")
    val positivoAlcohol: Boolean?,

    @field:Element(name = "positivoDroga")
    @param:Element(name = "positivoDroga")
    val positivoDroga: Boolean?
)

@Root(name = "accidentes")
class AccidenteListDto(
    @field:ElementList(name = "accidentes", inline = true)
    @param:ElementList(name = "accidentes", inline = true)
    val accidentes: List<AccidenteDto>
)