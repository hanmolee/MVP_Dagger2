package com.hanmo.dagger2_mvp_example.base

import android.support.v7.app.AppCompatActivity
import com.hanmo.dagger2_mvp_example.di.component.DaggerActivityComponent
import com.hanmo.dagger2_mvp_example.di.module.ActivityModule
import com.hanmo.dagger2_mvp_example.ui.repo.RepoActivity
import com.hanmo.dagger2_mvp_example.ui.search.SearchActivity

abstract class BaseActivity : AppCompatActivity() {

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is SearchActivity -> {
                DaggerActivityComponent.builder()
                        .activityModule(ActivityModule)
                        .build()
                        .inject(this)
            }

            is RepoActivity -> {
                DaggerActivityComponent.builder()
                        .activityModule(ActivityModule)
                        .build()
                        .inject(this)
            }
        }
    }

}