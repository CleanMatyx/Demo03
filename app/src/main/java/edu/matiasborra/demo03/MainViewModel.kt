package edu.matiasborra.demo03

import android.util.Log
import androidx.lifecycle.ViewModel
import edu.matiasborra.demo03.adapters.ItemsAdapter
import edu.matiasborra.edumatiasborrademo02.model.Items

/**
 * ViewModel principal de la aplicación
 * Contiene la lógica de negocio de la aplicación
 * @property _fragmentShowed fragmento mostrado en la actividad
 */
class MainViewModel : ViewModel() {
    private var _fragmentShowed: String? = null

    val fragmentShowed: String?
        get() = _fragmentShowed

    /**
     * Inicialización del ViewModel
     */
    init {
        Log.i("MainViewModel", "Init")
    }

    /**
     * Función para establecer el fragmento mostrado
     * @param fragmentName nombre del fragmento
     */
    fun setFragmentShowed(fragmentName: String) {
        _fragmentShowed = fragmentName
    }

    /**
     * Función para añadir un item a la lista
     * @param item item a añadir
     * Añade el item pasado por parámetro a la lista de items
     */
    fun addItem(item: Items) {
        Items.items.add(item)
        Log.i("MainViewModel", "addItem: $item")
    }

    /**
     * Función para obtener la lista de items
     * @return lista de items
     */
    fun fetchItems(): MutableList<Items> {
        Log.i("MainViewModel", "fetchItems: ${Items.items.size}")
        return Items.items.filter { it.archived == false }.toMutableList()
    }

    /**
     * Función para obtener la lista de items archivados
     * @return lista de items archivados
     */
    fun fetchArchivedItems(): MutableList<Items> {
        Log.i("MainViewModel", "fetchArchivedItems: ${Items.items.size}")
        return Items.items.filter { it.archived }.toMutableList()
    }

//    fun archiveItem(item: Items, adapter: ItemsAdapter) {
//        Items.items.remove(item)
//        item.archived = true
//        Items.items.add(item)
//
//        adapter.submitList(Items.items.filter { !it.archived }.toMutableList())
//        Log.i("MainViewModel", "archiveItem: $item")
//    }

    /**
     * Función para archivar un item
     * @param item item a archivar
     * @param callback función de callback
     * Archiva el item pasado por parámetro
     * Actualiza la lista de items
     */
    fun archiveItem(item: Items, callback: () -> Unit) {
        Items.items.remove(item)
        item.archived = true
        Items.items.add(item)
        callback()
    }

}