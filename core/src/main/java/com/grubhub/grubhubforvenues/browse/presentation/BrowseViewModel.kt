package com.grubhub.grubhubforvenues.browse.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.grubhub.grubhubforvenues.di.module.DataModule.Companion.BACKGROUND_SCHEDULER
import com.grubhub.grubhubforvenues.di.module.SearchModule.Companion.FETCH_EVENT_LIST_USE_CASE
import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.domain.UseCaseScheduler
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject
import javax.inject.Named

class BrowseViewModel
@Inject constructor(@Named(BACKGROUND_SCHEDULER) val scheduler: UseCaseScheduler,
                    @Named(FETCH_EVENT_LIST_USE_CASE) val fetchEventListUseCase: NoParamObservableUseCase<List<EventResponseModel>>,
                    val eventModelTransformer: IEventModelTransformer): ViewModel() {

    private var events: MutableLiveData<List<EventModel>> = MutableLiveData()

    fun events(): MutableLiveData<List<EventModel>> {
        return events
    }

    fun onResume() {
        scheduler.execute(fetchEventListUseCase.build(), EventFetchObserver())
    }

    fun onStop() {
        scheduler.dispose()
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