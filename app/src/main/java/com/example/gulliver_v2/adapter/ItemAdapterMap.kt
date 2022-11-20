package com.example.tiptime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gulliver_v2.R
import com.example.tiptime.model.Affirmation

class ItemAdapterMap(private val context: Context, private val dataset: List<Affirmation>, private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<ItemAdapterMap.ItemViewHolderMap>() {
    class ItemViewHolderMap(private val view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.item_title_map)
        val imageView: ImageView = view.findViewById(R.id.item_image_map)
        init {
            itemView.setOnClickListener({ _ -> onItemClicked(adapterPosition) })
        }
        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderMap {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_map, parent, false)

        return ItemViewHolderMap(adapterLayout, onItemClicked)
    }


    override fun onBindViewHolder(holder: ItemViewHolderMap, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }


    override fun getItemCount() = dataset.size


}