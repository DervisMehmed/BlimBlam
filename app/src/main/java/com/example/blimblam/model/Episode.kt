package com.example.blimblam.model

data class Episode(val id: Int, val name: String, val air_date: String, val episode: String,
              val characters: List<String>, val url: String, val created: String) {

    override fun toString(): String {
        return "Name: $name\n\nAir Date: $air_date\nEpisode: $episode"
    }
}
