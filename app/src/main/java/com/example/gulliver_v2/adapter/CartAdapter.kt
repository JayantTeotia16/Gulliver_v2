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

class CartAdapter(private val context: Context, private val dataset: List<Affirmation>, private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<CartAdapter.ViewHolderCart>() {
    class ViewHolderCart(private val view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.item_title_cart)
        val imageView: ImageView = view.findViewById(R.id.item_image_cart)
        init {
            itemView.setOnClickListener({ _ -> onItemClicked(adapterPosition) })
        }
        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_cart, parent, false)

        return ViewHolderCart(adapterLayout, onItemClicked)
    }


    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        val item = dataset[position]
        val text1 = context.resources.getString(item.stringResourceId)
        holder.textView.text =  text1+" Trip"
        holder.imageView.setImageResource(item.imageResourceId)
    }


    override fun getItemCount() = dataset.size


}