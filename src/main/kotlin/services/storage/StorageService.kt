package services.storage

interface StorageService<T> {
    fun guardarTodos(items: List<T>)
    fun cargarTodos(): List<T>
}