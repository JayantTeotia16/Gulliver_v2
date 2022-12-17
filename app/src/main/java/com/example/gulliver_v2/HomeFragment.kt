package com.example.gulliver_v2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.PopupWindow
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
    private lateinit var popupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val button = view.findViewById<Button>(R.id.create_trip_button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
        }
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        val recyclerView1 = view?.findViewById<RecyclerView>(R.id.recycler_view1)
        val myDataset = Datasource().loadAffirmations()
        //val position = 0
        recyclerView?.adapter = ItemAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})
        recyclerView1?.adapter = ItAdapter(this.requireContext(), myDataset, {position -> onListItemClick(position)})

        val popupViewJoinTrip = layoutInflater.inflate(R.layout.join_trip_popup, null)
        val popupWindowJoinTrip = PopupWindow(popupViewJoinTrip, 300, 700)
        val popButtonJoinTrip = view.findViewById<Button>(R.id.join_trip_button)
        popupWindowJoinTrip.isOutsideTouchable = true;
        popButtonJoinTrip.setOnClickListener {
            // Show the popup window
            popupWindowJoinTrip.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
        popupWindowJoinTrip.contentView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Dismiss the popup window
                popupWindowJoinTrip.dismiss()
                Log.d("DEBUG", "This is a debug message")

            }
            Log.d("DEBUG", "This is a d message")

            true
        }

        val popupViewCreateTrip = layoutInflater.inflate(R.layout.join_trip_popup, null)
        val popupWindowCreateTrip = PopupWindow(popupViewCreateTrip, 300, 700)
        val popButtonCreateTrip = view.findViewById<Button>(R.id.create_trip_button)
        popupWindowCreateTrip.isOutsideTouchable = true;
        popButtonCreateTrip.setOnClickListener {
            // Show the popup window
            popupWindowCreateTrip.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
        popupWindowCreateTrip.contentView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Dismiss the popup window
                popupWindowCreateTrip.dismiss()
            }
            true
        }

        return view
    }

    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this.requireContext(), myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }




}