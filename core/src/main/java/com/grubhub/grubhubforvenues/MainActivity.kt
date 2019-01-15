package com.grubhub.grubhubforvenues

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.grubhub.grubhubforvenues.browse.presentation.BrowseFragment
import com.grubhub.grubhubforvenues.history.presentation.HistoryActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getAppComponent(this).inject(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            BrowseFragment.newInstance(), BrowseFragment::javaClass.name
        ).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.browse, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.history -> {
            startActivity(HistoryActivity.intent(this))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
