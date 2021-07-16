package com.example.blimblam.ui.characters.detailCharScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Episode
import com.example.blimblam.model.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    private lateinit var call : Call<List<Episode>>
    private var epList = MutableLiveData<List<Episode>>()
    private var episodeTitle : String = "Episodes:\n\t\t"
    private var tempString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_detail_screen)
        initUI()
        char = intent.extras?.get("OBJECT") as Character
        intent.extras?.remove("OBJECT")
        loadUI(char)
        epList.observe(this, { it ->
            it.forEach {
                episodeTitle += it.name + "\n\t\t"
            }
            textViewEpisodes.text = episodeTitle
        })
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
        Picasso.get().load(character.image)
                .into(imageView)
        getEpName()
    }

    private fun getEpName(){
        tempString = char.episode.joinToString(",")
        tempString = tempString.filter { it.isDigit() || it == ',' }
        this.call = RetrofitClient.retroInterface
                .getEpisodes(tempString)
        loadData(call)
    }

    private fun loadData(call : Call<List<Episode>>) {
        call.enqueue(object  : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                epList.value = response.body()
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                Log.d("Failure", t.toString())
            }
        })
    }
}