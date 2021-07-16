package com.example.blimblam.ui.episodes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Episode
import com.example.blimblam.ui.characters.CharRecyclerViewAdapter
import com.example.blimblam.ui.detailScreen.DetailScreenActivity
import java.io.Serializable

class EpisodesFragment : Fragment() {
    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var epListCustomAdaptor: EpListCustomAdaptor
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var epView: RecyclerView
    private lateinit var buttonBack : Button
    private lateinit var buttonNext : Button
    private lateinit var root: View

    private lateinit var loadedData: MutableLiveData<List<Episode>>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        episodesViewModel =
                ViewModelProvider(this).get(EpisodesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_episodes, container, false)
        epView = root.findViewById(R.id.episodesListView)
        buttonBack = root.findViewById(R.id.buttonBack)
        buttonNext = root.findViewById(R.id.buttonNext)
        nestedScrollView = root.findViewById(R.id.nestedScrollView)
        linearLayoutManager = LinearLayoutManager(this.context)
        epView.layoutManager = linearLayoutManager
        loadedData = episodesViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            epListCustomAdaptor = loadedData.value?.let { EpListCustomAdaptor(this.activity, it)}!!
            epView.adapter = epListCustomAdaptor
        })

        buttonBack.setOnClickListener(View.OnClickListener {
            with(episodesViewModel){
                if(getInfoDTO().prev != null){
                    loadedData = loadLiveData(getInfoDTO().prev.last())
                    epListCustomAdaptor.notifyDataSetChanged()
                }
            }
        })

        buttonNext.setOnClickListener(View.OnClickListener {
            with(episodesViewModel){
                if(getInfoDTO().next != null){
                    loadedData = loadLiveData(getInfoDTO().next.last())
                    epListCustomAdaptor.notifyDataSetChanged()
                }
            }
        })

        return root
    }
}