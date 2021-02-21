package com.odukabdulbasit.rocketsworld.rocket_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odukabdulbasit.rocketsworld.R
import com.odukabdulbasit.rocketsworld.rocket_list.model.AllRocketsProperties

class AdapterAllRocketsList(val data : List<AllRocketsProperties>) : RecyclerView.Adapter<ViewHolderAllRockets>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAllRockets {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_list_item_all_rockets, parent, false)
        return ViewHolderAllRockets(
            view
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderAllRockets, position: Int) {
        var item = data[position]
        holder.rocketNameListItemTv.text = item.rocket_name
        holder.rocketIdListItemTV.text = item.rocket_id
        holder.rocketTypeListItemTV.text = item.first_flight


        Glide.with(holder.rocketListItemIV.context)
                .load(item.flickr_images.get(0))
                .into(holder?.rocketListItemIV)
    }
}
class ViewHolderAllRockets(view: View) : RecyclerView.ViewHolder(view) {

    val rocketListItemIV : ImageView = view.findViewById(R.id.rocketListItemIV)
    val rocketNameListItemTv : TextView = view.findViewById(R.id.rocketNameListItemTV)
    val rocketIdListItemTV  : TextView = view.findViewById(R.id.rocketIdListItemTV)
    val rocketTypeListItemTV  : TextView = view.findViewById(R.id.rocketTypeListItemTv)

}
