package com.example.blimblam.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.model.Episode

class EpisodesFragment : Fragment() {
    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var listViewAdapter : ArrayAdapter<*>
    private lateinit var episodeView: ListView
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
        episodeView = root.findViewById(R.id.episodesListView)
        loadedData = episodesViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let { it1 -> ArrayAdapter(it1.applicationContext,
                                                android.R.layout.simple_list_item_1, it) }!!
            episodeView.adapter = listViewAdapter
        })
        return root
    }
}