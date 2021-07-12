package com.example.blimblam.model

import com.google.gson.annotations.SerializedName

data class CharDataDTO(var info : Info, @SerializedName("results") var charResult : List<Character>)
