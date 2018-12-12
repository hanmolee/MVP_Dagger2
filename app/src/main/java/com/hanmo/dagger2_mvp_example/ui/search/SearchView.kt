package com.hanmo.dagger2_mvp_example.ui.search

import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.model.UserInfo

interface SearchView : BaseView {

    fun initButton()

    fun showUserInfo(userInfo: UserInfo)

    fun showLoading()

    fun hideLoading()

    fun showNotResult()

    fun startNextActivity(userName : String)
}