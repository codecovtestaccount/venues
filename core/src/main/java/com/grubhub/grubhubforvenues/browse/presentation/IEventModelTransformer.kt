package com.grubhub.grubhubforvenues.browse.presentation

import com.grubhub.venuesapi.model.EventResponseModel

interface IEventModelTransformer {
    fun transform(events: List<EventResponseModel>): List<EventModel>
}