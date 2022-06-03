package kieren.foenander.propertywatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kieren.foenander.propertywatch.database.Property

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kieren.foenander.propertywatch.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var mPrice: String? = null
    var mLatitude = 0.0
    var mLongitude = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null && intent.hasExtra("property")){
            val property = intent.getSerializableExtra("property") as Property

            mPrice = "$" + property.price.toString()
            mLatitude = property.lat
            mLongitude = property.lon

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val houseLocation = LatLng(mLatitude, mLongitude)
        mMap.addMarker(MarkerOptions().position(houseLocation).title(mPrice))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(houseLocation, 12f))
    }
}