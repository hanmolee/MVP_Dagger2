package com.hanmo.dagger2_mvp_example.base

import com.hanmo.dagger2_mvp_example.di.component.DaggerNetworkComponent
import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.ui.repo.RepoPresenter
import com.hanmo.dagger2_mvp_example.ui.search.SearchPresenter

abstract class BasePresenter<T> {

    init {
        inject()
    }

    open fun onViewCreated(view: T) {}

    open fun onViewDestroyed() {}

    private fun inject() {
        when(this) {
            is SearchPresenter -> {
                DaggerNetworkComponent.builder().networkModule(NetworkModule).build().inject(this)
            }

            is RepoPresenter -> {
                DaggerNetworkComponent.builder().networkModule(NetworkModule).build().inject(this)
            }
        }
    }

}