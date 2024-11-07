package edu.matiasborra.edumatiasborrademo02.model

//Anoto la clase con @Parcelize
data class Items(var id: Int, val title: String, val description: String, var image: String = ""){
    companion object {
        var identifier: Int = 0
        val items: MutableList<Items> = mutableListOf()
    }
    init {
        id = ++identifier
        image = "https://picsum.photos/200/200?image=$id"
    }
}