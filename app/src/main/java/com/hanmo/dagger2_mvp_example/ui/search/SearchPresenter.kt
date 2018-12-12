package com.hanmo.dagger2_mvp_example.ui.search

import com.hanmo.dagger2_mvp_example.base.BasePresenter
import com.hanmo.dagger2_mvp_example.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SearchPresenter : BasePresenter<SearchView>() {

    @Inject
    lateinit var apiService : ApiService

    private var searchView : SearchView? = null

    val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun onViewCreated(view: SearchView) {
        searchView = view
    }

    fun searchUserInfo(userNameText : String) {
        searchView?.showLoading()
        apiService.getUserInfo(userNameText)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { searchView?.hideLoading() }
                .subscribe(
                        { userInfo ->
                            userInfo?.run {
                                searchView?.showUserInfo(userInfo)
                            } ?: kotlin.run {
                                searchView?.showNotResult()
                            }
                        }, { error -> searchView?.showError(error.toString()) }
                )
                .apply { compositeDisposable.add(this) }
    }

    override fun onViewDestroyed() {
        searchView = null
        compositeDisposable.clear()
    }
}