package com.example.blimblam.model

import java.io.Serializable

data class Character(val id: Int, val name: String, val status: String, val species: String, val type: String,
                val gender: String, val origin: Origin, val location: Location, val image: String,
                val episode: List<String>, val url: String, val created: String) : Serializable {

    override fun toString(): String {
        return "Name: $name\n\nStatus: $status\nSpecies: $species\nType: $type\n" +
                "Gender: $gender\n\nOrigin:\t${origin.name}\nLocation:\t${location.name}"
    }
}
