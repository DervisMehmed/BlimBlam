package com.example.blimblam.model

import com.google.gson.annotations.SerializedName

data class CharDataDTO(val info : Info, @SerializedName("results") val charResult : List<Character>)
