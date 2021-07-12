package com.example.blimblam.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.ui.characters.detailCharScreen.DetailCharScreenFragment
import java.io.Serializable

class CharacterFragment : Fragment() {
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var listViewAdapter: CharacterAdapter
    private lateinit var charView: ListView
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
        loadedData = characterViewModel.loadLiveData()

        loadedData.observe(viewLifecycleOwner, Observer {
            listViewAdapter = activity?.let { it1 ->
                CharacterAdapter(it1.applicationContext, it)
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

        buttonBack.setOnClickListener(View.OnClickListener {
            with(characterViewModel){
                if(getInfoDTO().prev != null){
                    loadedData = loadLiveData(getInfoDTO().prev.last())
                    listViewAdapter.notifyDataSetChanged()
                }
            }
        })

        buttonNext.setOnClickListener(View.OnClickListener {
            with(characterViewModel){
                if(getInfoDTO().next != null){
                    loadedData = loadLiveData(getInfoDTO().next.last())
                    listViewAdapter.notifyDataSetChanged()
                }
            }
        })
        return root
    }
}