package com.example.blimblam.ui.detailScreen

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

class CustomAdapter(private val activity: Context?, private val arrData: List<Character>)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val characterName : TextView = view.findViewById(R.id.textViewResident)
        private val characterPic: ImageView? = view.findViewById(R.id.imageViewResident)
        var character: Character? = null

        fun bindCard(char: Character){
            this.character = char
            characterName.text = character!!.name
            Picasso.get().load(character!!.image)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.resident_cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = arrData[position]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }
}