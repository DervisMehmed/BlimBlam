package com.example.blimblam.model

import com.google.gson.annotations.SerializedName

data class LocationDataDTO(val info: Info, @SerializedName("results") val locResults: List<Location>)
