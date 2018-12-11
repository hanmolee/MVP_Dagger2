package com.hanmo.dagger2_mvp_example.ui

import android.content.Context
import android.os.Bundle
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.base.BaseActivity
import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.di.component.DaggerSearchComponent
import com.hanmo.dagger2_mvp_example.di.module.SearchModule
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
        DaggerSearchComponent.builder()
                .searchModule(SearchModule)
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchPresenter.onViewCreated(this)
    }


}