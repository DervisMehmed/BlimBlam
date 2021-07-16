package com.example.blimblam

import com.example.blimblam.model.CharDataDTO
import com.example.blimblam.model.Character
import com.example.blimblam.model.EpisodesDataDTO
import com.example.blimblam.model.LocationDataDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataService {
    @GET("/api/character")
    fun getCharData(@Query("page") page: Char? = null): Call<CharDataDTO>

    @GET("/api/episode")
    fun getEpData(@Query("page") page: Char? = null): Call<EpisodesDataDTO>

    @GET("/api/location")
    fun getLocData(@Query("page") page: Char? = null): Call<LocationDataDTO>

    @GET("/api/character/{number}")
    fun getCharacter(@Path("number") number: String? = null): Call<List<Character>>
}