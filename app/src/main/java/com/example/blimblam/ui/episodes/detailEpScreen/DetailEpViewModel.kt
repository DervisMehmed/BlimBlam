package com.example.blimblam.ui.episodes.detailEpScreen

import androidx.lifecycle.ViewModel
import com.example.blimblam.model.Episode

class DetailEpViewModel : ViewModel() {
    private lateinit var episode: Episode

    fun setChar(episode: Episode) {
        this.episode = episode
    }

    fun getEpisodeName(): String {
        return episode.name + "\n"
    }

    fun getEpisodeAirdate(): String {
        return "Air Date : " + episode.air_date + "\n"
    }

    fun getEpisodeEpisode(): String {
        return "Episode : " + episode.episode + "\n"
    }

    fun getEpisodeURL(): String {
        return "URL : " + episode.url + "\n"
    }

    fun getEpisodeCreated(): String {
        return "Created: " + episode.created + "\n"
    }

    fun getEpisodeCharacters(): List<String> {
        return episode.characters + "\n"
    }
}