package com.example.blimblam.ui.detailScreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Episode
import com.example.blimblam.model.Location
import com.example.blimblam.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailScreenActivity : AppCompatActivity() {
    private lateinit var textViewName: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textViewResidents: TextView
    private lateinit var residentRecyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    private var charList = MutableLiveData<List<Character>>()
    private lateinit var call : Call<List<Character>>
    private var characterTitle : String = "Residents:\n"
    private var tempString: String = ""
    private lateinit var objEp : Episode
    private lateinit var objLoc : Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        textViewName = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textViewResidents = findViewById(R.id.textViewCharTag)
        residentRecyclerView = findViewById(R.id.recyclerViewResidents)
        gridLayoutManager = GridLayoutManager(applicationContext, 4)
        residentRecyclerView.layoutManager = gridLayoutManager

        if( intent.extras?.get("OBJECT") is Episode){
            objEp = intent.extras?.get("OBJECT") as Episode
            intent.extras?.remove("OBJECT")
            loadUIEp(objEp)
        } else if (intent.extras?.get("OBJECT") is Location){
            objLoc = intent.extras?.get("OBJECT") as Location
            intent.extras?.remove("OBJECT")
            loadUILoc(objLoc)
        }
        charList.observe( this , Observer{
            customAdapter = CustomAdapter(applicationContext, it)
            residentRecyclerView.adapter = customAdapter
        })
    }

    private fun loadUILoc(obj: Location) {
        textViewName.text = "${obj.name}"
        textView2.text = "Type:\t${obj.type}"
        textView3.text = "Dimension:\t${obj.dimension}"
        textViewResidents.text = characterTitle
        getLocationResidents()
    }

    private fun loadUIEp(obj: Episode){
        textViewName.text = "${obj.name}"
        textView2.text = "Air Date:\t${obj.air_date}"
        textView3.text = "Episode:\t${obj.episode}"
        textViewResidents.text = characterTitle
        getEpResidents()
    }

    fun getLocationResidents(){
        tempString = objLoc.residents.joinToString(",")
        tempString = tempString.filter { it.isDigit() || it == ',' }
        this.call = RetrofitClient.retroInterface
                .getCharacter(tempString)
        loadData(call)
    }

    fun getEpResidents(){
        tempString = objEp.characters.joinToString(",")
        tempString = tempString.filter { it.isDigit() || it == ',' }
        this.call = RetrofitClient.retroInterface
                .getCharacter(tempString)
        loadData(call)
    }

    private fun loadData(call : Call<List<Character>>) {
        call.enqueue(object  : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                charList.value = response.body()
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Log.d("Failure", t.toString())
            }
        })
    }
}