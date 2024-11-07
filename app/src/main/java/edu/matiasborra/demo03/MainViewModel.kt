package edu.matiasborra.demo03

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _fragmentShowed: String? = null
    val fragmentShowed: String?
        get() = _fragmentShowed

    fun setFragmentShowed(fragmentName: String) {
        _fragmentShowed = fragmentName

    }
}