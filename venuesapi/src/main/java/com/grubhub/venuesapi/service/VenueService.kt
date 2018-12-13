package com.grubhub.venuesapi.service

import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueService {

    @GET("browse")
    fun events(@Query("includeSuggested") includeSuggested: Boolean): Observable<List<EventResponseModel>>

    companion object Factory {
        fun create(): VenueService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://webservices.vividseats.com/rest/mobile/v1/home/")
                .build()

            return retrofit.create(VenueService::class.java)
        }
    }
}