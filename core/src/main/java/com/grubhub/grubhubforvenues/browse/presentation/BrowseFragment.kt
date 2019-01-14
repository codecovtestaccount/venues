package com.grubhub.grubhubforvenues.browse.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grubhub.grubhubforvenues.BaseApplication
import com.grubhub.grubhubforvenues.R
import com.grubhub.grubhubforvenues.di.module.DataModule
import com.grubhub.grubhubforvenues.di.module.DataModule.Companion.IO_SCHEDULER
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_browse.view.*
import javax.inject.Inject
import javax.inject.Named

class BrowseFragment : Fragment() {

    @Inject
    lateinit var viewModel: BrowseViewModel

    @Inject
    @field:Named(IO_SCHEDULER)
    lateinit var ioScheduler: Scheduler

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var adapter: RecyclerEventListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        BaseApplication.getAppComponent(this.requireActivity()).inject(this)
        val root = inflater.inflate(R.layout.fragment_browse, container, false)

        adapter = RecyclerEventListAdapter(this.requireContext())
        root.event_list.layoutManager = LinearLayoutManager(this.context)
        root.event_list.adapter = adapter

        compositeDisposable.add(adapter.getPositionClicks().subscribeOn(ioScheduler).subscribe {
            Log.i("Browse", "Event: " + it.name)
        })

        viewModel.events().observe(this, Observer { adapter.setEvents(it) })

        return root
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

    companion object {
        @JvmStatic
        fun newInstance() = BrowseFragment()
    }
}
