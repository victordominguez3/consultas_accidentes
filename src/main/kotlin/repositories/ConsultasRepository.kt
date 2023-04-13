package repositories

import enums.Sexo
import models.Accidente
import `typealias`.ListaAccidentes
import java.io.File
import java.time.Month

interface ConsultasRepository : CrudRepository<Accidente, String> {
    fun positivoAlcoholODrogas(): ListaAccidentes
    fun numPositivoAlcoholYDrogas(): Int
    fun agruparPorSexo(): Map<Sexo, ListaAccidentes>
    fun numAccidentesPorMeses(): Map<Month, Int>
    fun agruparPorTipoVehiculo(): Map<String, ListaAccidentes>
    fun enCalleLeganes(): ListaAccidentes
    fun numAccidentesPorDistrito(): Map<String, Int>
    fun distritoMasAccidentes(): String
    fun distritoMenosAccidentes(): String
    fun porDistritoDescendentemente(): Map<String, ListaAccidentes>
    fun accidentesFinSemanaNoche(): ListaAccidentes
    fun accidentesFinSemanaNochePositivoAlcohol(): ListaAccidentes
    fun accidentesUnoOMasDeUnFallecido(): ListaAccidentes
    fun distritoMasAccidentesFinSemana(): String
    fun numAccidentesAlcoholODrogasYMortal(): Int
    fun numAccidentesAtropelloPersona(): Int
    fun porMeteorologia(): Map<String?, ListaAccidentes>
    fun accidentesAtropelloAnimal(): ListaAccidentes
}