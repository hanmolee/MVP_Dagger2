package com.hanmo.dagger2_mvp_example.di.module

import com.hanmo.dagger2_mvp_example.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    internal fun provideSearchPresenter() : SearchPresenter {
        return SearchPresenter()
    }

}