package com.odin.kweatherapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.odin.kweatherapp.Models.CityPreferences

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.getItemId() == R.id.action_settings) {
            findPlace(window.decorView.rootView.findViewById(R.id.activity_main))
        }
        return false
    }

    fun changeCity(city: String) {

        val cityPreferences = CityPreferences(this)
        cityPreferences.setCity(city)

        val refresh = Intent(this, MainActivity::class.java)
        startActivity(refresh)//Start the same Activity
        finish()
    }

    fun findPlace(view: View) {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this@MainActivity)
            startActivityForResult(intent, 1)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("flag", "add")
        } catch (e: GooglePlayServicesRepairableException) {
            Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_LONG).show()
        } catch (e: GooglePlayServicesNotAvailableException) {
            Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_LONG).show()
            // TODO: Handle the error.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weatherFragment = WeatherFragment()
        val detailFragment = DetailFragment()
        if (savedInstanceState == null) {
            val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment1, weatherFragment)
            ft.add(R.id.fragment2, detailFragment)
            ft.commit()
        }
        val swipeRefreshLayout = findViewById(R.id.activity_main) as SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            val refresh = Intent(this@MainActivity, MainActivity::class.java)
            refresh.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            refresh.putExtra("flag", "add")
            startActivity(refresh)
            finish()
            overridePendingTransition(0, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(this, data)
                changeCity(place.name.toString())
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(this, data)
                // TODO: Handle the error.
                Log.e("Tag", status.statusMessage)

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
                Toast.makeText(this@MainActivity, "User canceled operation", Toast.LENGTH_LONG).show()
            }
        }
    }
}
