package com.nelson.le
//Nelson Le - 301064831

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var placeName: String
    private var placeLatitude: Double = 0.0
    private var placeLongitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        // Retrieve the selected place details
        placeName = intent.getStringExtra("PLACE_NAME") ?: ""
        placeLatitude = intent.getDoubleExtra("PLACE_LATITUDE", 0.0)
        placeLongitude = intent.getDoubleExtra("PLACE_LONGITUDE", 0.0)

        // Initialize the map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Enable the back button in the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Display the selected place on the map
        displayPlaceOnMap(googleMap)
    }

    private fun displayPlaceOnMap(googleMap: GoogleMap) {
        // Add a marker for the selected place
        val location = LatLng(placeLatitude, placeLongitude)
        googleMap.addMarker(MarkerOptions().position(location).title(placeName))

        // Move camera to the selected place
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }
}
