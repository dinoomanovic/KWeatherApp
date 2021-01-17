package com.odin.kweatherapp

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.odin.kweatherapp.models.CityPreferences
import com.odin.kweatherapp.ui.WeatherDetailsFragment
import com.odin.kweatherapp.ui.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var startForResult: ActivityResultLauncher<Intent>? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_settings) {
            findPlace()
        }
        return false
    }

    private fun changeCity(city: String) {
        val preferences: SharedPreferences = this.getPreferences(Activity.MODE_PRIVATE)

        val cityPreferences = CityPreferences(preferences)
        cityPreferences.setCity(city)

        val refresh = Intent(this, MainActivity::class.java)
        startActivity(refresh) //Start the same Activity
        finish()
    }

    private fun findPlace() {
        try {
            val fields = listOf(Place.Field.ID, Place.Field.NAME)

            // Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this)
            startForResult?.launch(intent)
        } catch (ex:Exception) {
            Toast.makeText(this@MainActivity, ex.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Places.
        Places.initialize(this, "YOUR_API_KEY");

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == 1) {
                when (result.resultCode) {
                    Activity.RESULT_OK -> {
                        result.data?.let {
                            val place = Autocomplete.getPlaceFromIntent(it)
                            changeCity(place.name.toString())
                        }

                    }
                    AutocompleteActivity.RESULT_ERROR -> {
                        result.data?.let {
                            val status = Autocomplete.getStatusFromIntent(it)
                            // TODO: Handle the error.
                            Log.e("Tag", status.statusMessage)
                        }
                    }
                    Activity.RESULT_CANCELED -> {
                        // The user canceled the operation.
                        Toast.makeText(this@MainActivity, "User canceled operation", Toast.LENGTH_LONG).show()
                    }
                }
                return@registerForActivityResult
            }
        }

        val weatherFragment = WeatherFragment()
        val detailFragment = WeatherDetailsFragment()
        if (savedInstanceState == null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment1, weatherFragment)
            ft.add(R.id.fragment2, detailFragment)
            ft.commit()
        }
        swipeRefreshLayout.setOnRefreshListener {
            val refresh = Intent(this@MainActivity, MainActivity::class.java)
            refresh.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            refresh.putExtra("flag", "add")
            startActivity(refresh)
            finish()
            overridePendingTransition(0, 0)
        }
    }
}
