package com.example.blimblam.ui.detailCharScreen

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.blimblam.model.Character
import com.squareup.picasso.Picasso

class DetailCharScreenViewModel : ViewModel() {
    private lateinit var character: Character

    fun setChar(character: Character) {
        this.character = character
    }

    fun getCharacterName(): String {
        return character.name
    }

    fun getCharacterStatus(): String {
        return "Status: " + character.status
    }

    fun getCharacterSpecies(): String {
        return "Species: " + character.species
    }

    fun getCharacterType(): String {
        return "Type: " + character.type
    }

    fun getCharacterGender(): String {
        return "Gender: " + character.gender
    }

    fun getCharacterOrigin(): String {
        return "Origin: " + character.origin.name
    }

    fun getCharacterLocation(): String {
        return "Location:\n\t\tName: ${character.location.name}\n\t\tType: ${character.location.type}\n\t\tDimension: ${character.location.dimension}"
    }

    fun getCharacterEpisodes(): List<String> {
        return character.episode
    }

    fun getCharacterCreated(): String{
        return "Created: " + character.created
    }

    fun loadImage(imageView: ImageView){
        Picasso.get().load(character.image)
            .into(imageView)
    }
}