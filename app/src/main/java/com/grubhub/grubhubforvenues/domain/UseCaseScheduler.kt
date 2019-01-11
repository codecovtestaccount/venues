package com.grubhub.grubhubforvenues.domain

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

interface UseCaseScheduler {

    fun <K, T> execute(single: Single<T>, observer: K) where K : SingleObserver<T>, K : Disposable

    fun dispose()
}