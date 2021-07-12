package com.example.blimblam.ui.episodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesViewModel : ViewModel() {
    private var episodeData = MutableLiveData<List<Episode>>()
    private lateinit var call : Call<EpisodesDataDTO>
    private lateinit var epDataDTO: EpisodesDataDTO

    fun loadLiveData(page: Char? = null): MutableLiveData<List<Episode>> {
        this.call = getServiceCall(page)
        return loadData(call)
    }

    fun getInfoDTO() : Info{
        return epDataDTO.info
    }

    private fun getServiceCall(page: Char? = null) : Call<EpisodesDataDTO> {
        return RetrofitClient.retroInterface.getEpData(page)
    }

    private fun loadData(call : Call<EpisodesDataDTO>) : MutableLiveData<List<Episode>> {
        call.enqueue(object  : Callback<EpisodesDataDTO> {

            override fun onResponse(call: Call<EpisodesDataDTO>, response: Response<EpisodesDataDTO>) {
                val body = response.body()
                if (body != null) {
                    epDataDTO = EpisodesDataDTO(body.info, body.episodeResults)
                    episodeData.value = body.episodeResults
                }
            }

            override fun onFailure(call: Call<EpisodesDataDTO>, t: Throwable) {
                println("**** Failure ****\n$t")
            }
        })
        return episodeData
    }
}