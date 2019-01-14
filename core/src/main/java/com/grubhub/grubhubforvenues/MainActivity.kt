package com.grubhub.grubhubforvenues

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grubhub.grubhubforvenues.browse.presentation.BrowseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getAppComponent(this).inject(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,
            BrowseFragment.newInstance(), BrowseFragment::javaClass.name).commit()
    }
}
