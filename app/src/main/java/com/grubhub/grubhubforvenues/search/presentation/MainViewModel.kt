package com.grubhub.grubhubforvenues.search.presentation

import android.util.Log
import com.grubhub.grubhubforvenues.domain.Notifier
import com.grubhub.grubhubforvenues.search.domain.FetchEventListUseCase
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class MainViewModel @Inject constructor() {

    @Inject
    lateinit var fetchEventListUseCase: FetchEventListUseCase
    @Inject
    lateinit var eventModelTransformer: EventModelTransformer
    var subject: Subject<MainViewModelListener> = PublishSubject.create()

    private val subscriptions = CompositeDisposable()

    fun subject(): Observable<MainViewModelListener> {
        return subject
    }

    fun onResume() {
        val d = fetchEventListUseCase.build()
            .doOnSubscribe {
                Log.d("MainViewModel", "test more stuff")
            }
            .doOnError {
                Log.e("MainViewModel", it.message)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                subject.map { t -> t.onEventsLoaded(eventModelTransformer.transform(it)) }
            }, {
                Log.e("MainViewModel", it.message)
            })
        subscriptions.add(d)
    }

    fun onStop() {
        subscriptions.dispose()
    }

    interface MainViewModelListener {
        fun onEventsLoaded(events: List<EventModel>)
    }
}