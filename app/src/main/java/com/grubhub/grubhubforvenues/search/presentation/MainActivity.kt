package com.grubhub.grubhubforvenues.search.presentation

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.grubhub.grubhubforvenues.BaseApplication
import com.grubhub.grubhubforvenues.R
import com.grubhub.grubhubforvenues.databinding.ActivityMainBinding
import com.grubhub.venuesapi.service.VenueService
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainViewModel.MainViewModelListener {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject lateinit var venueService: VenueService
    @Inject lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getAppComponent(this).inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)

        compositeDisposable.add(viewModel.subject().subscribe())
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onEventsLoaded(events: List<EventModel>) {
        Log.i(this.javaClass.simpleName, events.toString())
    }
}
