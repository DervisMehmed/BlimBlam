package com.example.blimblam

import com.example.blimblam.model.CharDataDTO
import com.example.blimblam.model.EpisodesDataDTO
import com.example.blimblam.model.LocationDataDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("/api/character")
    fun getCharData(@Query("page") page: Int? = null): Call<CharDataDTO>

    @GET("/api/episode")
    fun getEpData(@Query("page") page: Int? = null): Call<EpisodesDataDTO>

    @GET("/api/location")
    fun getLocData(@Query("page") page: Int? = null): Call<LocationDataDTO>
}