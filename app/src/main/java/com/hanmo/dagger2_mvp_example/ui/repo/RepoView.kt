package com.hanmo.dagger2_mvp_example.ui.repo

import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.model.Repo

interface RepoView : BaseView {

    fun updateRepo(repo : List<Repo>)

    fun showRefreshing()

    fun hideRefreshing()

    fun getUserName() : String
}