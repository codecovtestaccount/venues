package com.grubhub.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grubhub.details.R
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val eventModel: EventModel = intent.getParcelableExtra(EVENT)

        name.text = eventModel.name
        date.text = eventModel.date
    }

    companion object {

        const val EVENT = "EVENT"

        @JvmStatic
        fun intent(context: Context?, eventModel: EventModel): Intent {
            return Intent(context, EventActivity::class.java).apply {
                putExtra(EVENT, eventModel)
            }
        }
    }
}
