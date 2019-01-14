package com.grubhub.grubhubforvenues.browse.presentation

import com.grubhub.grubhubforvenues.di.domain.TestUseCaseScheduler
import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.domain.UseCaseScheduler
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BrowseViewModelTest {

    var useCaseScheduler: UseCaseScheduler = TestUseCaseScheduler()
    @Mock
    lateinit var fetchEventListUseCase: NoParamObservableUseCase<List<EventResponseModel>>
    @Mock
    lateinit var transformer: IEventModelTransformer

    private lateinit var target: BrowseViewModel

    @Before
    fun setup() {
        target = BrowseViewModel(useCaseScheduler, fetchEventListUseCase, transformer)
    }

    @Test
    fun onResume_fetchesEvents() {
        Mockito.`when`(fetchEventListUseCase.build()).thenReturn(Single.just(ArrayList()))

        target.onResume()

        Mockito.verify(fetchEventListUseCase).build()
    }
}