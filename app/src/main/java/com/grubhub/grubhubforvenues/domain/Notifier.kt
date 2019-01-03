package com.grubhub.grubhubforvenues.domain

interface Notifier<ListenerT> {
    fun notify(listener: ListenerT)
}