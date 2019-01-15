package com.grubhub.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grubhub.details.R

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
    }

    companion object {
        @JvmStatic
        fun Intent(context: Context?): Intent {
            return Intent(context, EventActivity::class.java)
        }
    }
}
