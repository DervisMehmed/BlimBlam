package com.example.blimblam.ui.episodes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Episode
import com.example.blimblam.ui.detailScreen.DetailScreenActivity
import java.io.Serializable

class EpListCustomAdaptor (private val activity: Context?, private val arrData: List<Episode>)
    : RecyclerView.Adapter<EpListCustomAdaptor.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val listItem1 : TextView = view.findViewById(R.id.textViewListItem1)
        private val listItem2 : TextView = view.findViewById(R.id.textViewListItem2)
        private val listItem3 : TextView = view.findViewById(R.id.textViewListItem3)
        var episode: Episode? = null

        fun bindCard(char: Episode){
            this.episode = char
            listItem1.text = episode!!.name
            listItem2.text = episode!!.air_date
            listItem3.text = episode!!.episode
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v?.context
            val intent = Intent(context, DetailScreenActivity::class.java)
            intent.putExtra("OBJECT", episode as Serializable)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.listitem_cardview, parent, false)
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