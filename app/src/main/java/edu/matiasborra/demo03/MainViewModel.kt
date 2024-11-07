package edu.matiasborra.demo03

import android.util.Log
import androidx.lifecycle.ViewModel

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
}