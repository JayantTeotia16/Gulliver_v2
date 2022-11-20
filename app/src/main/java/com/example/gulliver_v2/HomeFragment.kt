package com.example.gulliver_v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.adapter.ItAdapter
import com.example.tiptime.adapter.ItemAdapter
import com.example.tiptime.data.Datasource
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView: NavigationView
    lateinit var toolbar: Toolbar

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
        val button2 = view.findViewById<Button>(R.id.create_trip_button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        button1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_GMapsFragment)
        }
        button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
        }
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        val recyclerView1 = view?.findViewById<RecyclerView>(R.id.recycler_view1)
        val myDataset = Datasource().loadAffirmations()
        //val position = 0
        recyclerView?.adapter = ItemAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})
        recyclerView1?.adapter = ItAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})



        return view
    }

    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this.requireContext(), myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }




}