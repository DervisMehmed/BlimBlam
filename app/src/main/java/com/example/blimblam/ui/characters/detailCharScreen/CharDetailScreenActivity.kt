package com.example.blimblam.ui.characters.detailCharScreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Episode
import com.squareup.picasso.Picasso

class CharDetailScreenActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewSpecies: TextView
    private lateinit var textViewType: TextView
    private lateinit var textViewGender: TextView
    private lateinit var textViewOrigin: TextView
    private lateinit var textViewLocation: TextView
    private lateinit var textViewEpisodes: TextView
    private lateinit var char: Character
    private val episodeTitle : String = "Episodes:\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_detail_screen)
        initUI()
        char = intent.extras?.get("OBJECT") as Character
        intent.extras?.remove("OBJECT")
        loadUI(char)
    }

    private fun initUI() {
        imageView = findViewById(R.id.imageViewCharacter)
        textViewName = findViewById(R.id.textViewName)
        textViewStatus = findViewById(R.id.textViewStatus)
        textViewSpecies = findViewById(R.id.textViewSpecies)
        textViewType = findViewById(R.id.textViewType)
        textViewGender = findViewById(R.id.textViewGender)
        textViewOrigin = findViewById(R.id.textViewOrigin)
        textViewLocation = findViewById(R.id.textViewLocation)
        textViewEpisodes = findViewById(R.id.textViewEpisodes)
    }
    @SuppressLint("SetTextI18n")
    private fun loadUI(character: Character){
        textViewName.text = character.name
        textViewStatus.text = "Status:\t" + character.status
        textViewSpecies.text = "Species:\t" + character.species
        textViewType.text = "Type:\t" + character.type
        textViewGender.text = "Gender:\t" + character.gender
        textViewOrigin.text = "Origin:\t" + character.origin.name
        textViewLocation.text = "Location:\n\t\tName: ${character.location.name}\n\t\t" +
                "Type: ${character.location.type}\n\t\t" +
                "Dimension: ${character.location.dimension}"
        textViewEpisodes.text = character.episode.toString()
        Picasso.get().load(character.image)
                .into(imageView)
    }
}