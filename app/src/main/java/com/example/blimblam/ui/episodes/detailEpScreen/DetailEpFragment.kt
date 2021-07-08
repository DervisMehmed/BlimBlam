package com.example.blimblam.ui.episodes.detailEpScreen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Episode

class DetailEpFragment : Fragment() {

    companion object {
        fun newInstance() = DetailEpFragment()
    }

    private lateinit var viewModel: DetailEpViewModel
    private lateinit var char: Episode
    private lateinit var root: View
    private lateinit var value: Bundle

    private lateinit var textViewName: TextView
    private lateinit var textViewAirdate: TextView
    private lateinit var textViewEpisode: TextView
    private lateinit var textViewCharacters: TextView
    private lateinit var textViewURL: TextView
    private lateinit var textViewEPCreated: TextView
    private val characterTitle : String = "Characters:\n"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailEpViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_detail_ep, container, false)
        value = this.requireArguments()

        char = value.getSerializable("obj") as Episode

        with(root) {
            textViewName = findViewById(R.id.textViewEPName)
            textViewAirdate = findViewById(R.id.textViewEPAirdate)
            textViewEpisode = findViewById(R.id.textViewEpisode)
            textViewCharacters = findViewById(R.id.textViewCharacters)
            textViewURL = findViewById(R.id.textViewURL)
            textViewEPCreated = findViewById(R.id.textViewEPCreated)
        }
        with(viewModel){
            setChar(char)
            textViewName.text = getEpisodeName()
            textViewAirdate.text = getEpisodeAirdate()
            textViewEpisode.text = getEpisodeEpisode()
            textViewCharacters.text = characterTitle + getEpisodeCharacters().toString()
            textViewURL.text = getEpisodeURL()
            textViewEPCreated.text = getEpisodeCreated()
        }
        return root
    }
}