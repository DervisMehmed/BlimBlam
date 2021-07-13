package com.example.blimblam.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Character

class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var charView: RecyclerView
    private lateinit var charRecyclerViewAdapter: CharRecyclerViewAdapter
    private lateinit var buttonBack : Button
    private lateinit var buttonNext : Button
    private lateinit var root: View

    private var loadedData = MutableLiveData<List<Character>>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        characterViewModel =
                ViewModelProvider(this).get(CharacterViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_characters, container, false)
        charView = root.findViewById(R.id.characterListView)
        buttonBack = root.findViewById(R.id.buttonBack)
        buttonNext = root.findViewById(R.id.buttonNext)
        linearLayoutManager = LinearLayoutManager(this.context)
        charView.layoutManager = linearLayoutManager
        loadedData = characterViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            charRecyclerViewAdapter = loadedData.value?.let { CharRecyclerViewAdapter(this.activity, it)}!!
            charView.adapter = charRecyclerViewAdapter
        })

        buttonBack.setOnClickListener(View.OnClickListener {
            with(characterViewModel){
                if(getInfoDTO().prev != null){
                    loadedData = loadLiveData(getInfoDTO().prev.last())
                    charRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })

        buttonNext.setOnClickListener(View.OnClickListener {
            with(characterViewModel){
                if(getInfoDTO().next != null){
                    loadedData = loadLiveData(getInfoDTO().next.last())
                    charRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })
        return root
    }
}