package com.grubhub.grubhubforvenues

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.grubhub.venuesapi.service.VenueService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var venueService: VenueService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getAppComponent(this).inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }
}
