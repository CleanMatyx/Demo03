package edu.matiasborra.demo03

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.matiasborra.edumatiasborrademo02.model.Items
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
//        Items.items.add(item)
//        Log.i("MainViewModel", "addItem: $item")
        viewModelScope.launch{
            delay(1000)
            Items.items.add(item)
        }
    }

    fun fetchItems(): MutableList<Items> {
        Log.i("MainViewModel", "fetchItems: ${Items.items.size}")
        return Items.items.filter { it.archived == false }.toMutableList()
    }
}