package com.hanmo.dagger2_mvp_example.di.module

import com.hanmo.dagger2_mvp_example.ui.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
object SearchModule {

    @Provides
    internal fun providePresenter() : SearchPresenter {
        return SearchPresenter()
    }
}