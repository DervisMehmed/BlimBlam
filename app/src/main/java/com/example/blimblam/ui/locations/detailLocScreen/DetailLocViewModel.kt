package com.example.blimblam.ui.locations.detailLocScreen

import androidx.lifecycle.ViewModel
import com.example.blimblam.model.Location

class DetailLocViewModel : ViewModel() {
    private lateinit var location: Location

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
}