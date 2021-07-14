package com.example.blimblam.ui.locations.detailLocScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.ui.characters.detailCharScreen.DetailCharScreenFragment
import com.example.blimblam.ui.locations.detailLocationScreen.DetailLocFragment
import com.squareup.picasso.Picasso
import java.io.Serializable

class CustomAdapter(private val activity: Context?, private val arrData: MutableList<Character>)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val characterName : TextView = view.findViewById(R.id.textViewResident)
        private val characterPic: ImageView? = view.findViewById(R.id.imageViewResident)
        var character: Character? = null

        fun bindCard(char: Character){
            this.character = char
            characterName.text = char.name
            Picasso.get().load(char.image)
                    .into(characterPic)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val fragment: Fragment = DetailCharScreenFragment()
            val args = Bundle()
            args.putSerializable("obj", character as Serializable?)
            fragment.arguments = args
            v!!.findFragment<DetailLocFragment>().parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.resident_cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val model = arrData[position]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }
}