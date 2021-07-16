package com.example.blimblam.ui.locations.detailLocScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Location

class DetailLocFragment : Fragment() {
    companion object {
        fun newInstance() = DetailLocFragment()
    }
    private lateinit var viewModel: DetailLocViewModel
    private lateinit var char: Location
    private lateinit var root: View
    private lateinit var value: Bundle

    private lateinit var textViewName: TextView
    private lateinit var textViewType: TextView
    private lateinit var textViewDimension: TextView
    private lateinit var textViewResidents: TextView
    private lateinit var gridLayoutManager: LinearLayoutManager
    private lateinit var residentRecyclerView: RecyclerView
    private lateinit var mutableList: MutableLiveData<MutableList<Character>>
    private lateinit var customAdapter: CustomAdapter
    private lateinit var textViewURL: TextView
    private lateinit var textViewCreated: TextView
    private val characterTitle : String = "Residents:\n"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailLocViewModel::class.java)
        root = inflater.inflate(R.layout.detail_loc_fragment, container, false)
        //gridLayoutManager = LinearLayoutManager(this.context)
        value = this.requireArguments()
        char = value.getSerializable("obj") as Location

        with(root) {
            textViewName = findViewById(R.id.textViewLocName)
            textViewType = findViewById(R.id.textViewLocType)
            textViewDimension = findViewById(R.id.textViewLocDimension)
            textViewResidents = findViewById(R.id.textViewLocResidents)
            textViewURL = findViewById(R.id.textViewLocURL)
            textViewCreated = findViewById(R.id.textViewLocCreated)
            //residentRecyclerView = findViewById(R.id.recyclerViewResidents)
            //residentRecyclerView.layoutManager = gridLayoutManager
        }
        with(viewModel) {
            setChar(char)
            textViewName.text = getLocationName()
            textViewType.text = getLocationType()
            textViewDimension.text = getLocationDimension()
            textViewResidents.text = characterTitle + getLocationResidents()
            textViewURL.text = getLocationURL()
            textViewCreated.text = getLocationCreated()
            //mutableList = viewModel.loadResidents(char.residents)
        }
        //  TODO
        /*mutableList.observe(viewLifecycleOwner, {
            customAdapter = mutableList.value?.let { CustomAdapter(context, it) }!!
            residentRecyclerView.adapter = customAdapter
        })*/
        return root
    }
}