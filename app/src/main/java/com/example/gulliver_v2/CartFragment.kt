package com.example.gulliver_v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.adapter.CartAdapter
import com.example.tiptime.adapter.ItemAdapter
import com.example.tiptime.data.Datasource


class CartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_cart)
        val myDataset = Datasource().loadAffirmations()
        //val position = 0
        recyclerView?.adapter = CartAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})

        return view
    }
    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this.requireContext(), myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }

}