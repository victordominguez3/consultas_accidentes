package repositories

interface CrudRepository<T, ID> {
    fun buscarTodos(): List<T>
    fun buscarPorId(id: ID): T?
    fun guardar(item: T): T?
    fun actualizar(item: T): List<T>?
    fun eliminarPorId(id: ID): List<T>?
}