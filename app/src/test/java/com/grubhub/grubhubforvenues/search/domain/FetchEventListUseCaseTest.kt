package com.grubhub.grubhubforvenues.search.domain

import com.google.common.truth.Truth
import com.grubhub.grubhubforvenues.search.data.EventRepository
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchEventListUseCaseTest {

    val EVENTS: ArrayList<EventResponseModel> = ArrayList()

    @Mock
    lateinit var eventRepository: IEventRepository
    @InjectMocks
    lateinit var fetchEventListUseCase: FetchEventListUseCase

    @Before
    fun setup() {
        Mockito.`when`(eventRepository.getEvents()).thenReturn(Single.just(EVENTS))
    }

    @Test
    fun whenFetch_returnEvents() {
        val testObserver: TestObserver<List<EventResponseModel>> = fetchEventListUseCase.build().test()
        testObserver.assertComplete()

        Truth.assertThat(testObserver.values()[0]).isEqualTo(EVENTS)
    }
}