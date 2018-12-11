package com.hanmo.dagger2_mvp_example.ui

import com.hanmo.dagger2_mvp_example.base.BasePresenter
import com.hanmo.dagger2_mvp_example.base.BaseView

class SearchPresenter : BasePresenter<BaseView> {

    private var searchView : BaseView? = null

    override fun onViewCreated(view: BaseView) {
        searchView = view
    }

    override fun onViewDestroyed() {
        searchView = null
    }
    

}