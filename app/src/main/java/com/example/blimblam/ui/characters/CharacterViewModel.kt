package com.example.blimblam.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.CharDataDTO
import com.example.blimblam.model.Character
import com.example.blimblam.model.Info
import com.example.blimblam.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    var charData = MutableLiveData<List<Character>>()
    private lateinit var call : Call<CharDataDTO>
    private lateinit var charDataDTO: CharDataDTO

    fun loadLiveData(page: Char? = null): MutableLiveData<List<Character>> {
        this.call = getServiceCall(page)
        return loadData(call)
    }

    fun getInfoDTO() : Info{
        return charDataDTO.info
    }

    private fun getServiceCall(page: Char? = null) : Call<CharDataDTO> {
        return RetrofitClient.retroInterface.getCharData(page)
    }

    private fun loadData(call : Call<CharDataDTO>) : MutableLiveData<List<Character>> {
        call.enqueue(object  : Callback<CharDataDTO> {

            override fun onResponse(call: Call<CharDataDTO>, response: Response<CharDataDTO>) {
                val body = response.body()
                if (body != null) {
                    charDataDTO = CharDataDTO(body.info, body.charResult)
                    charData.postValue(body.charResult)
                }
            }

            override fun onFailure(call: Call<CharDataDTO>, t: Throwable) {
                println("**** Failure ****\n$t")
            }
        })
        return charData
    }
}