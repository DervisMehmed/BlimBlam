package com.example.blimblam.ui.characters.detailCharScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blimblam.R
import com.example.blimblam.model.Character

class DetailCharScreenFragment : Fragment() {
    private lateinit var detailCharScreenFragment: DetailCharScreenViewModel
    private lateinit var char: Character
    private lateinit var root: View
    private lateinit var value: Bundle

    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewSpecies: TextView
    private lateinit var textViewType: TextView
    private lateinit var textViewGender: TextView
    private lateinit var textViewOrigin: TextView
    private lateinit var textViewLocation: TextView
    private lateinit var textViewEpisodes: TextView
    private lateinit var textViewCreated: TextView

    private val episodeTitle : String = "Episodes:\n"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailCharScreenFragment =
                ViewModelProvider(this).get(DetailCharScreenViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_detail_char_screen, container, false)
        value = this.requireArguments()

        if ( value != null)
            char = value.getSerializable("obj") as Character

        with(root){
            imageView = findViewById(R.id.imageViewCharacter)
            textViewName = findViewById(R.id.textViewName)
            textViewStatus = findViewById(R.id.textViewStatus)
            textViewSpecies = findViewById(R.id.textViewSpecies)
            textViewType = findViewById(R.id.textViewType)
            textViewGender = findViewById(R.id.textViewGender)
            textViewOrigin = findViewById(R.id.textViewOrigin)
            textViewLocation = findViewById(R.id.textViewLocation)
            textViewEpisodes = findViewById(R.id.textViewEpisodes)
            textViewCreated = findViewById(R.id.textViewCreated)
        }

        with(detailCharScreenFragment){
            setChar(char)
            loadImage(imageView)
            textViewName.text = getCharacterName()
            textViewStatus.text = getCharacterStatus()
            textViewSpecies.text= getCharacterSpecies()
            textViewType.text = getCharacterType()
            textViewGender.text = getCharacterGender()
            textViewOrigin.text = getCharacterOrigin()
            textViewLocation.text = getCharacterLocation()
            textViewEpisodes.text = episodeTitle + getCharacterEpisodes().toString()
            textViewCreated.text = getCharacterCreated()
        }
        return root
    }
}