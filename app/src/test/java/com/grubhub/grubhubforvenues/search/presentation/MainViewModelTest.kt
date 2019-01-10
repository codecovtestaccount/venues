package com.grubhub.grubhubforvenues.search.presentation

import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.search.domain.FetchEventListUseCase
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var transformer: IEventModelTransformer
    @Mock
    lateinit var fetchEventListUseCase: NoParamObservableUseCase<List<EventResponseModel>>
    @InjectMocks
    lateinit var target: MainViewModel

    @Test
    fun events() {

    }

    @Test
    fun onResume_fetchesEvents() {
//        Mockito.`when`(fetchEventListUseCase.build()).thenReturn(Single.just(ArrayList()))
//
//        target.onResume()
//
//        Mockito.verify(fetchEventListUseCase).build()
    }

    @Test
    fun onStop() {
    }
}