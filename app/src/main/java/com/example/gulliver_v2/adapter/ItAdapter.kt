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

class ItAdapter(private val context: Context, private val dataset: List<Affirmation>, private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<ItAdapter.ItemViewHolder1>() {
    class ItemViewHolder1(private val view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.item_title1)
        val imageView: ImageView = view.findViewById(R.id.item_image1)
        init {
            itemView.setOnClickListener({ _ -> onItemClicked(adapterPosition) })
        }
        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder1 {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item1, parent, false)

        return ItemViewHolder1(adapterLayout, onItemClicked)
    }


    override fun onBindViewHolder(holder: ItemViewHolder1, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }


    override fun getItemCount() = dataset.size


}