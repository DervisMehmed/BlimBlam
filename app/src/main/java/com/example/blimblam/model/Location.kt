package com.example.blimblam.model

data class Location(val id: Int, val name: String, val type: String, val dimension: String,
                    val residents: List<String>, val url: String, val created: String) {

    override fun toString(): String {
        return "Name: $name\n\nType: $type\nDimension: $dimension"
    }
}