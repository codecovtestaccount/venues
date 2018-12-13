package com.grubhub.venuesapi.model

data class EventResponseModel(val id: Int,
                              val name: String,
                              val eventCount: Int,
                              val rank: Int,
                              val venue: String,
                              val city: String,
                              val state: String,
                              val performerHeroImage: String,
                              val categoryHeroImage: String,
                              val venueDisplay: String,
                              val dateDisplay: String)