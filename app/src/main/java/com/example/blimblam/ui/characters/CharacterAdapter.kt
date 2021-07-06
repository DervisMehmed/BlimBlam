package com.example.blimblam.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter() : Adapter<CharacterAdapter.CharacterHolder>() {
    private lateinit var dataList: List<Character>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterHolder {
        val itemV : View = LayoutInflater.from(parent.context).inflate(R.layout.character_cardview,
                                        parent, false)
        return CharacterHolder(itemV)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val currentChar : Character = dataList[position]
        holder.characterName.text = currentChar.name
        holder.characterStatus.text = currentChar.status
        holder.characterOrigin.text = currentChar.origin.name
        Picasso.get().load(currentChar.image)
            .into(holder.characterPic)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setDataList(list : List<Character>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    class CharacterHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var characterName : TextView = itemView.findViewById(R.id.textViewName)
        var characterStatus: TextView = itemView.findViewById(R.id.textViewStatus)
        var characterOrigin: TextView = itemView.findViewById(R.id.textViewOrigin)
        var characterPic: ImageView = itemView.findViewById(R.id.imageViewCharacter)
    }
}