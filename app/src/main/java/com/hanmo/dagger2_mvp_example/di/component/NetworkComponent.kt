package com.hanmo.dagger2_mvp_example.di.component

import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.ui.repo.RepoPresenter
import com.hanmo.dagger2_mvp_example.ui.search.SearchPresenter
import dagger.Component


@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun inject(searchPresenter: SearchPresenter)
    fun inject(repoPresenter: RepoPresenter)
}
