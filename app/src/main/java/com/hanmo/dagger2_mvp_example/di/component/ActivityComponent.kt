package com.hanmo.dagger2_mvp_example.di.component

import com.hanmo.dagger2_mvp_example.di.module.ActivityModule
import com.hanmo.dagger2_mvp_example.di.module.ContextModule
import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.ui.search.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ActivityModule::class])
interface ActivityComponent {

    fun inject(searchActivity: SearchActivity)
}