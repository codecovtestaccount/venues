package com.grubhub.details.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventModel(val name: String, val imageUrl: String, val date: String): Parcelable