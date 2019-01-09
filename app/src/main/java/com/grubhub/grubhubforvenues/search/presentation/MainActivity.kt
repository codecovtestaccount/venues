package com.grubhub.grubhubforvenues.search.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.grubhub.grubhubforvenues.BaseApplication
import com.grubhub.grubhubforvenues.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainViewModel

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var adapter: RecyclerEventListAdapter = RecyclerEventListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getAppComponent(this).inject(this)
        setContentView(R.layout.activity_main)

        event_list.layoutManager = LinearLayoutManager(this)
        event_list.adapter = adapter

        viewModel.events().observe(this, Observer { adapter.setEvents(it) })
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
}
