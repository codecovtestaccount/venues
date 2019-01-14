package com.grubhub.grubhubforvenues.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BackgroundScheduler constructor(private val ioScheduler: Scheduler, private val uiScheduler: Scheduler) : UseCaseScheduler {

    private val disposables = CompositeDisposable()

    override fun <K, T> execute(single: Single<T>, observer: K) where K : SingleObserver<T>, K : Disposable {
        disposables.add(
            single.subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribeWith(observer)
        )
    }

    override fun dispose() {
        disposables.clear()
    }
}