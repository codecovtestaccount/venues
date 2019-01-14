package com.grubhub.grubhubforvenues.di.domain

import com.grubhub.grubhubforvenues.domain.UseCaseScheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.TestScheduler

class TestUseCaseScheduler: UseCaseScheduler {

    override fun <K, T> execute(single: Single<T>, observer: K) where K : SingleObserver<T>, K : Disposable {
        TestScheduler().triggerActions()
    }

    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}