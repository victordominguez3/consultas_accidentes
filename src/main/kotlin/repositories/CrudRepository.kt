package repositories

interface CrudRepository<T, ID> {
    fun importar(list: List<T>)
    fun exportar(): List<T>
    fun buscarTodos(): List<T>
    fun buscarPorId(id: ID): T?
    fun guardar(item: T): T?
    fun actualizar(item: T): List<T>?
    fun eliminarPorId(id: ID): List<T>?
}