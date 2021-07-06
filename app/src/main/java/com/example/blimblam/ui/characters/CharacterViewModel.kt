package com.example.blimblam.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.CharDataDTO
import com.example.blimblam.model.Character
import com.example.blimblam.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    var charData = MutableLiveData<List<Character>>()
    private lateinit var call : Call<CharDataDTO>

    fun loadLiveData(): MutableLiveData<List<Character>> {
        this.call = getServiceCall()
        return loadData(call)
    }

    private fun getServiceCall() : Call<CharDataDTO> {
        return RetrofitClient.retroInterface.getCharData()
    }

    private fun loadData(call : Call<CharDataDTO>) : MutableLiveData<List<Character>> {
        call.enqueue(object  : Callback<CharDataDTO> {

            override fun onResponse(call: Call<CharDataDTO>, response: Response<CharDataDTO>) {
                val body = response.body()
                if (body != null) {
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