package com.grubhub.grubhubforvenues.history.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grubhub.grubhubforvenues.R

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }

    companion object {

        @JvmStatic
        fun intent(context: Context?): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }
}
