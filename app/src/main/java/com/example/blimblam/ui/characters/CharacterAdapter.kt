package com.example.blimblam.ui.characters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(var context: Context, var charList: List<Character>) : BaseAdapter() {

    override fun getCount(): Int {
        return charList.size
    }

    override fun getItem(position: Int): Any {
        return charList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.character_cardview, null)
        val characterName : TextView = view.findViewById(R.id.character_name)
        val characterStatus: TextView = view.findViewById(R.id.character_status)
        val characterOrigin: TextView = view.findViewById(R.id.character_origin)
        val characterPic: ImageView? = view.findViewById(R.id.character_pic)

        val char: Character = charList[position]

        characterName.text = char.name
        characterStatus.text = char.status
        characterOrigin.text = char.origin.name
        Picasso.get().load(char.image)
                .into(characterPic)
        return view
    }
}