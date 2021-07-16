package com.example.blimblam.ui.locations

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blimblam.R
import com.example.blimblam.model.Location
import com.example.blimblam.ui.detailScreen.DetailScreenActivity
import java.io.Serializable

class LocListCustomAdapter (private val activity: Context?, private val arrData: List<Location>)
    : RecyclerView.Adapter<LocListCustomAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {
        private val listItem1 : TextView = view.findViewById(R.id.textViewListItem1)
        private val listItem2 : TextView = view.findViewById(R.id.textViewListItem2)
        private val listItem3 : TextView = view.findViewById(R.id.textViewListItem3)
        var location: Location? = null

        fun bindCard(char: Location){
            this.location = char
            listItem1.text = location!!.name
            listItem2.text = location!!.type
            listItem3.text = location!!.dimension
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = v?.context
            val intent = Intent(context, DetailScreenActivity::class.java)
            intent.putExtra("OBJECT", location as Serializable)
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