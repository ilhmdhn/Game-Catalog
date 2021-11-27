package com.ilhmdhn.gamecatalog.di

import com.ilhmdhn.gamecatalog.core.domain.usecase.GameInteractor
import com.ilhmdhn.gamecatalog.core.domain.usecase.GameUseCase
import com.ilhmdhn.gamecatalog.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}