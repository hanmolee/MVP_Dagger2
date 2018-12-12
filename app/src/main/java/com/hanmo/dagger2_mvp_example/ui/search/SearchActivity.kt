package com.hanmo.dagger2_mvp_example.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.base.BaseActivity
import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.di.component.DaggerActivityComponent
import com.hanmo.dagger2_mvp_example.di.module.ActivityModule
import com.hanmo.dagger2_mvp_example.model.UserInfo
import com.hanmo.dagger2_mvp_example.ui.repo.RepoActivity
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @Inject
    lateinit var searchPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchPresenter.onViewCreated(this)
    }

    override fun initButton() {
        searchButton.clicks()
                .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .filter { inputUserName.text.isNotEmpty() }
                .subscribe {
                    searchPresenter.searchUserInfo(inputUserName.text.toString())
                }
                .apply { searchPresenter.compositeDisposable.add(this) }

        userInfoForm.clicks()
                .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .filter { userInfoForm.visibility == View.VISIBLE }
                .subscribe {
                    searchPresenter.clickUserInfo()
                }
                .apply { searchPresenter.compositeDisposable.add(this) }
    }


    override fun showUserInfo(userInfo: UserInfo) {
        notResultText.visibility = View.GONE
        userInfoForm.visibility = View.VISIBLE

        Glide.with(getContext()).load(userInfo.avatar_url).into(profileImg)
        userNameText.text = userInfo.name
        userBlogText.text = userInfo.blog
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNotResult() {
        notResultText.visibility = View.VISIBLE
        userInfoForm.visibility = View.GONE
    }

    override fun startNextActivity(userName: String) {
        val nextActivity = RepoActivity.newIntent(getContext(), userName)
        startActivity(nextActivity)
    }

    override fun onDestroy() {
        searchPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun getContext(): Context {
        return this
    }

    override fun showError(error: String) {
        toast(error)
    }

}