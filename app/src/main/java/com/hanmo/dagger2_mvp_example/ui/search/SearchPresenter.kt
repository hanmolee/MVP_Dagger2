package com.hanmo.dagger2_mvp_example.ui.search

import android.util.Log
import com.hanmo.dagger2_mvp_example.base.BasePresenter
import com.hanmo.dagger2_mvp_example.base.BaseView
import com.hanmo.dagger2_mvp_example.di.component.DaggerSearchComponent
import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SearchPresenter : BasePresenter<BaseView> {

    @Inject
    lateinit var apiService : ApiService

    private var searchView : BaseView? = null

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerSearchComponent.builder().networkModule(NetworkModule).build().inject(this)
    }

    override fun onViewCreated(view: BaseView) {
        searchView = view

        Log.e("hahanmo onCreateview", "aaa")
        loadData()
    }

    private fun loadData() {
        Log.e("hahanmo loadData", "loaa")

        apiService.getUserInfo("hanmolee")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { Log.e("hahanmo error ", it.toString()) }
                .subscribe({
                    Log.e("hahanmo res ", it.toString())
                }, {

                }).apply { compositeDisposable.add(this) }
    }

    override fun onViewDestroyed() {
        searchView = null
        compositeDisposable.clear()
    }


}