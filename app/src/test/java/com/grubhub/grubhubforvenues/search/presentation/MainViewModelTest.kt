package com.grubhub.grubhubforvenues.search.presentation

import com.grubhub.grubhubforvenues.search.domain.FetchEventListUseCase
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
    lateinit var useCase: FetchEventListUseCase
    @InjectMocks
    lateinit var target: MainViewModel

    @Test
    fun events() {

    }

    @Test
    fun onResume_fetchesEvents() {
        target.onResume()

        Mockito.verify(useCase).build()
    }

    @Test
    fun onStop() {
    }
}