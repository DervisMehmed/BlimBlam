package com.example.blimblam.ui.characters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Character
import com.example.blimblam.ui.characters.detailCharScreen.CharDetailScreenActivity
import com.squareup.picasso.Picasso
import java.io.Serializable

class CharRecyclerViewAdapter(private val activity: Context?, private val arrData: List<Character>)
    : RecyclerView.Adapter<CharRecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val characterName : TextView = view.findViewById(R.id.character_name)
        private val characterStatus: TextView = view.findViewById(R.id.character_status)
        private val characterOrigin: TextView = view.findViewById(R.id.character_origin)
        private val characterPic: ImageView? = view.findViewById(R.id.character_pic)
        var character: Character? = null

        fun bindCard(char: Character){
            this.character = char
            characterName.text = char.name
            characterStatus.text = char.status
            characterOrigin.text = char.origin.name
            Picasso.get().load(char.image)
                    .into(characterPic)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v?.context
            val intent = Intent(context, CharDetailScreenActivity::class.java)
            intent.putExtra("OBJECT", character as Serializable)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharRecyclerViewAdapter.ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.character_cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharRecyclerViewAdapter.ViewHolder, position: Int) {
        val model = arrData[position]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }
}