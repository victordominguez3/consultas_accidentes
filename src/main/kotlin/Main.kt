import repositories.ConsultasRepositoryMemory

const val MORADO = "\u001B[35m"
const val RESET = "\u001B[0m"

fun main(args: Array<String>) {

    val controller = ConsultasRepositoryMemory()

//    controller.escribirJson()
//    controller.escribirXml()
    for (i in controller.accidentes) {
        println(i)
    }

}