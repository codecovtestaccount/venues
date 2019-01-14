package com.grubhub.grubhubforvenues.search.data

import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single

interface IEventRepository {
    fun getEvents(): Single<List<EventResponseModel>>
}