package com.grubhub.grubhubforvenues.domain

import io.reactivex.Single

interface NoParamObservableUseCase<R> {
    fun build(): Single<R>
}