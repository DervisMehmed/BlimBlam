package com.example.blimblam.ui.characters

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
import com.example.blimblam.ui.detailCharScreen.DetailCharScreenFragment
import java.io.Serializable


class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var listViewAdapter: ArrayAdapter<*>
    private lateinit var charView: ListView
    private lateinit var root: View
    private lateinit var loadedData: MutableLiveData<List<Character>>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        characterViewModel =
                ViewModelProvider(this).get(CharacterViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_characters, container, false)
        charView = root.findViewById(R.id.characterListView)
        loadedData = characterViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let { it1 ->
                ArrayAdapter(it1.applicationContext,
                        android.R.layout.simple_list_item_1, it)
            }!!
            charView.adapter = listViewAdapter
        })

        charView.setOnItemClickListener { parent, view, position, id ->
            val fragment: Fragment = DetailCharScreenFragment()
            val args = Bundle()
            args.putSerializable("obj", parent.adapter.getItem(position) as Serializable?)
            fragment.arguments = args

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
        return root
    }
}