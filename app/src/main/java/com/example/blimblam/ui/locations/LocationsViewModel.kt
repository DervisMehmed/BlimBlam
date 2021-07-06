package com.example.blimblam.ui.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationsViewModel : ViewModel() {
    private var locationData = MutableLiveData<List<Location>>()
    private lateinit var call : Call<LocationDataDTO>

    fun loadLiveData(): MutableLiveData<List<Location>> {
        this.call = getServiceCall()
        return loadData(call)
    }

    private fun getServiceCall() : Call<LocationDataDTO> {
        return RetrofitClient.retroInterface.getLocData()
    }

    private fun loadData(call : Call<LocationDataDTO>) : MutableLiveData<List<Location>> {
        call.enqueue(object  : Callback<LocationDataDTO> {

            override fun onResponse(call: Call<LocationDataDTO>, response: Response<LocationDataDTO>) {
                val body = response.body()
                if (body != null) {
                    locationData.value = body.locResults
                }
            }

            override fun onFailure(call: Call<LocationDataDTO>, t: Throwable) {
                println("**** Failure ****\n$t")
            }
        })
        return locationData
    }
}