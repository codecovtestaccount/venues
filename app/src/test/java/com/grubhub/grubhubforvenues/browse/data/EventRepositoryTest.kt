package com.grubhub.grubhubforvenues.browse.data

import com.google.common.truth.Truth
import com.grubhub.venuesapi.model.EventResponseModel
import com.grubhub.venuesapi.service.VenueService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventRepositoryTest {

    val EVENTS: ArrayList<EventResponseModel> = ArrayList()

    @Mock
    lateinit var venueService: VenueService
    @InjectMocks
    lateinit var target: EventRepository

    @Before
    fun setup() {
        Mockito.`when`(venueService.events(eq((true)))).thenReturn(Single.just(EVENTS))
    }

    @Test
    fun getEvents() {
        Truth.assertThat(target.getEvents().blockingGet()).isEqualTo(EVENTS)
    }
}