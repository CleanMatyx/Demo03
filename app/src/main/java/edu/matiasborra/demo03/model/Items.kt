package edu.matiasborra.edumatiasborrademo02.model

/**
 * Clase Items
 * @param id: Int
 * @param title: String
 * @param description: String
 * @param image: String
 * @param archived: Boolean
 */
//Anoto la clase con @Parcelize
data class Items(var id: Int = 0, var title: String, var description: String, var image: String = "",
                 var archived: Boolean = false){
    companion object {

        var identifier: Int = 0
        val items: MutableList<Items> = mutableListOf()
    }
    init {
        id = ++identifier
        image = "https://picsum.photos/200/200?image=$id"
    }
}