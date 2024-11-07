package edu.matiasborra.demo03

import android.util.Log
import androidx.lifecycle.ViewModel
import edu.matiasborra.demo03.adapters.ItemsAdapter
import edu.matiasborra.edumatiasborrademo02.model.Items

class MainViewModel : ViewModel() {
    private var _fragmentShowed: String? = null

    val fragmentShowed: String?
        get() = _fragmentShowed

    init {
        Log.i("MainViewModel", "Init")
    }

    fun setFragmentShowed(fragmentName: String) {
        _fragmentShowed = fragmentName
    }

    fun addItem(item: Items) {
        Items.items.add(item)
        Log.i("MainViewModel", "addItem: $item")
    }

    fun fetchItems(): MutableList<Items> {
        Log.i("MainViewModel", "fetchItems: ${Items.items.size}")
        return Items.items.filter { it.archived == false }.toMutableList()
    }

    fun fetchArchivedItems(): MutableList<Items> {
        Log.i("MainViewModel", "fetchArchivedItems: ${Items.items.size}")
        return Items.items.filter { it.archived }.toMutableList()
    }

    fun archiveItem(item: Items, adapter: ItemsAdapter) {
        Items.items.remove(item)
        item.archived = true
        Items.items.add(item)

        adapter.submitList(Items.items.filter { !it.archived }.toMutableList())
        Log.i("MainViewModel", "archiveItem: $item")
    }

}