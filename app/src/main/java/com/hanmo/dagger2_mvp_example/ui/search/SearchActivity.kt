package com.hanmo.dagger2_mvp_example.ui.search

import android.content.Context
import android.os.Bundle
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.base.BaseActivity
import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.di.component.DaggerActivityComponent
import com.hanmo.dagger2_mvp_example.di.module.ActivityModule
import org.jetbrains.anko.toast
import javax.inject.Inject

class SearchActivity : BaseActivity(), BaseView {

    override fun getContext(): Context {
        return this
    }

    override fun showError(error: String) {
        toast(error)
    }

    @Inject
    lateinit var searchPresenter: SearchPresenter

    init {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule)
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchPresenter.onViewCreated(this)
    }

    override fun onDestroy() {
        searchPresenter.onViewDestroyed()
        super.onDestroy()
    }

}