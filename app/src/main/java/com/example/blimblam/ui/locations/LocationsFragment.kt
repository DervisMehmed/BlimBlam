package com.example.blimblam.ui.locations

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
import com.example.blimblam.model.Location
import com.example.blimblam.ui.detailScreen.DetailScreenActivity
import com.example.blimblam.ui.episodes.EpListCustomAdaptor
import java.io.Serializable

class LocationsFragment : Fragment() {
    private lateinit var locationsViewModel: LocationsViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var locListCustomAdaptor: LocListCustomAdapter
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var locationView: RecyclerView
    private lateinit var buttonBack : Button
    private lateinit var buttonNext : Button
    private lateinit var root: View

    private lateinit var loadedData: MutableLiveData<List<Location>>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        locationsViewModel =
                ViewModelProvider(this).get(LocationsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_locations, container, false)
        locationView = root.findViewById(R.id.locationsListView)
        nestedScrollView = root.findViewById(R.id.nestedScrollView)
        linearLayoutManager = LinearLayoutManager(this.context)
        locationView.layoutManager = linearLayoutManager
        buttonBack = root.findViewById(R.id.buttonBack)
        buttonNext = root.findViewById(R.id.buttonNext)
        loadedData = locationsViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            locListCustomAdaptor = loadedData.value?.let { LocListCustomAdapter(this.activity, it)}!!
            locationView.adapter = locListCustomAdaptor
        })

        buttonBack.setOnClickListener(View.OnClickListener {
            with(locationsViewModel){
                if(getInfoDTO().prev != null){
                    loadedData = loadLiveData(getInfoDTO().prev.last())
                    locListCustomAdaptor.notifyDataSetChanged()
                }
            }
        })

        buttonNext.setOnClickListener(View.OnClickListener {
            with(locationsViewModel){
                if(getInfoDTO().next != null){
                    loadedData = loadLiveData(getInfoDTO().next.last())
                    locListCustomAdaptor.notifyDataSetChanged()
                }
            }
        })
        return root
    }
}