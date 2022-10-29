package com.example.gulliver_v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.adapter.ItemAdapter
import com.example.tiptime.data.Datasource
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.NonDisposableHandle.parent


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        val button = view.findViewById<Button>(R.id.service_button1)
        val button1 = view.findViewById<Button>(R.id.service_button2)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_clickFragment)
        }

        button1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_GMapsFragment)
        }

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        val myDataset = Datasource().loadAffirmations()
        //val position = 0
        recyclerView?.adapter = ItemAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})



        return view
    }

    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this.requireContext(), myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }


}