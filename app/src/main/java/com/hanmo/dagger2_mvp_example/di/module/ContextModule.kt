package com.hanmo.dagger2_mvp_example.di.module

import android.app.Application
import android.content.Context
import com.hanmo.dagger2_mvp_example.base.BaseView
import dagger.Module
import dagger.Provides

@Module
object ContextModule {

    @Provides
    fun provideContext(baseView: BaseView) : Context {
        return baseView.getContext()
    }

    @Provides
    fun provideApplication(context: Context) : Application {
        return context.applicationContext as Application
    }

}