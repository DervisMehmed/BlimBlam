package com.example.blimblam.ui.locations.detailLocScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.Character
import com.example.blimblam.model.Location
import com.example.blimblam.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLocViewModel : ViewModel() {
    private lateinit var call : Call<Character>
    private lateinit var location: Location
    private var characterList = MutableLiveData<MutableList<Character>>()
    private var tempList = mutableListOf<Character>()
    private var character: Character? = null

    fun setChar(location: Location) {
        this.location = location
    }

    fun getLocationName(): String {
        return location.name + "\n"
    }

    fun getLocationType(): String {
        return "Type : " + location.type + "\n"
    }

    fun getLocationDimension(): String {
        return "Location : " + location.dimension + "\n"
    }

    fun getLocationURL(): String {
        return "URL : " + location.url + "\n"
    }

    fun getLocationCreated(): String {
        return "Created: " + location.created + "\n"
    }

    fun getLocationResidents(): String {
        return location.residents.toString() + "\n"
    }

    fun loadResidents(list: List<String>): MutableLiveData<MutableList<Character>> {
        for (charURL in list){
            this.call = RetrofitClient.retroInterface
                    .getCharacter(charURL.substring((charURL.lastIndexOf('/')+1)))
            loadData(call)
        }
        characterList.value = tempList
        return characterList
    }

    private fun loadData(call : Call<Character>) {
        call.enqueue(object  : Callback<Character> {

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                character = response.body()
                tempList.add(character!!)
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d("Failure", t.toString())
            }
        })
    }
}