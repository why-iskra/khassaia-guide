package ru.unit.repository_places.models

data class PlaceModel(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val type: Type,
    val imgUrl: String,
    val name: String,
    val description: String? = null,
    val stars: Int? = null,
    val rating: Float? = null,
    val price: Float? = null,
) {
    enum class Type {
        PLACE,
        CAFE,
        HOTEL
    }
}
