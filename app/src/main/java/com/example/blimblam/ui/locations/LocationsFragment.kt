package com.example.blimblam.ui.locations

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
import com.example.blimblam.model.Location

class LocationsFragment : Fragment() {
    private lateinit var locationsViewModel: LocationsViewModel
    private lateinit var listViewAdapter : ArrayAdapter<*>
    private lateinit var locationView: ListView
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
        locationView= root.findViewById(R.id.locationsListView)
        loadedData = locationsViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let { it1 -> ArrayAdapter(it1.applicationContext,
                    android.R.layout.simple_list_item_1, it) }!!
            locationView.adapter = listViewAdapter
        })
        return root
    }
}