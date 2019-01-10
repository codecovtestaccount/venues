package com.grubhub.grubhubforvenues.search.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(): ViewModel() {

    @Inject
    @field:Named("FetchEventListUseCase")
    lateinit var fetchEventListUseCase: NoParamObservableUseCase<List<EventResponseModel>>
    @Inject
    lateinit var eventModelTransformer: IEventModelTransformer

    private var events: MutableLiveData<List<EventModel>> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    fun events(): MutableLiveData<List<EventModel>> {
        return events
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

        override fun onSuccess(e: List<EventResponseModel>) {
            events.value = eventModelTransformer.transform(e)
        }

        override fun onError(e: Throwable) {
            TODO("not implemented")
        }
    }
}