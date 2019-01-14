package com.grubhub.grubhubforvenues.search.presentation

import com.google.common.truth.Truth.assertThat
import com.grubhub.venuesapi.model.EventResponseModel
import org.junit.Test

class EventModelTransformerTest {

    private val EVENT_NAME = "Test Name"
    private val EVENT_IMAGE_URL = "performerHeroImage"
    private val EVENT_DATE = "dateDisplay"

    private val EVENTS = listOf(EventResponseModel(
        12,
        EVENT_NAME,
        1,
        2,
        "Test Venue",
        "Test City",
        "IL",
        EVENT_IMAGE_URL,
        "categoryHeroImage",
        "venueDisplay",
        EVENT_DATE))

    private val target = EventModelTransformer()

    @Test
    fun transform() {
        val events = target.transform(EVENTS)

        assertThat(events).hasSize(1)
        val eventModel = events[0]
        assertThat(eventModel.name).isEqualTo(EVENT_NAME)
        assertThat(eventModel.date).isEqualTo(EVENT_DATE)
        assertThat(eventModel.imageUrl).isEqualTo(EVENT_IMAGE_URL)
    }
}