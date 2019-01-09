package com.grubhub.grubhubforvenues.search.presentation

import com.grubhub.grubhubforvenues.search.domain.FetchEventListUseCase
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
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
        subscriptions.add(fetchEventListUseCase.build()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(EventFetchObserver()))
    }

    fun onStop() {
        subscriptions.dispose()
    }

    inner class EventFetchObserver: DisposableSingleObserver<List<EventResponseModel>>() {

        override fun onSuccess(events: List<EventResponseModel>) {

        }

        override fun onError(e: Throwable) {
            TODO("not implemented")
        }
    }

    interface MainViewModelListener {
        fun onEventsLoaded(events: List<EventModel>)
    }
}