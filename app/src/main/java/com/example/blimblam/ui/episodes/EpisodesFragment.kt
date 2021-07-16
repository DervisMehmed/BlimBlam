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
import com.example.blimblam.R
import com.example.blimblam.model.Episode
import com.example.blimblam.ui.detailScreen.DetailScreenActivity
import java.io.Serializable

class EpisodesFragment : Fragment() {
    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var listViewAdapter : ArrayAdapter<*>
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var episodeView: ListView
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
        episodeView = root.findViewById(R.id.episodesListView)
        buttonBack = root.findViewById(R.id.buttonBack)
        buttonNext = root.findViewById(R.id.buttonNext)
        nestedScrollView = root.findViewById(R.id.nestedScrollView)
        loadedData = episodesViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let { it1 -> ArrayAdapter(it1.applicationContext,
                                                android.R.layout.simple_list_item_1, it) }!!
            episodeView.adapter = listViewAdapter
        })

        episodeView.setOnItemClickListener { parent, view, position, id ->
            val context = view.context
            val intent = Intent(context, DetailScreenActivity::class.java)
            intent.putExtra("OBJECT", parent.adapter.getItem(position) as Serializable)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        buttonBack.setOnClickListener(View.OnClickListener {
            with(episodesViewModel){
                if(getInfoDTO().prev != null){
                    loadedData = loadLiveData(getInfoDTO().prev.last())
                    listViewAdapter.notifyDataSetChanged()
                }
            }
        })

        buttonNext.setOnClickListener(View.OnClickListener {
            with(episodesViewModel){
                if(getInfoDTO().next != null){
                    loadedData = loadLiveData(getInfoDTO().next.last())
                    listViewAdapter.notifyDataSetChanged()
                }
            }
        })

        return root
    }
}