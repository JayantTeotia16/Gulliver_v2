package com.example.gulliver_v2

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.adapter.ItemAdapter
import com.example.tiptime.adapter.ItemAdapterMap
import com.example.tiptime.data.Datasource

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GMapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(12.9415, 77.6178)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Koramangala"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_g_maps, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_map)
        val myDataset = Datasource().loadAffirmations()
        //val position = 0
        recyclerView?.adapter = ItemAdapterMap(this.requireContext(), myDataset, {position -> onListItemClick(position)})
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this.requireContext(), myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }
}