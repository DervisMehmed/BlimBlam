package com.example.blimblam.model

import com.google.gson.annotations.SerializedName

data class EpisodesDataDTO(val info: Info, @SerializedName("results") val episodeResults: List<Episode>)
