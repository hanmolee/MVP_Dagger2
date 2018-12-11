package com.hanmo.dagger2_mvp_example.di.component

import com.hanmo.dagger2_mvp_example.di.module.ContextModule
import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.di.module.SearchModule
import com.hanmo.dagger2_mvp_example.ui.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, SearchModule::class])
interface SearchComponent {

    fun inject(searchActivity: SearchActivity)
}
