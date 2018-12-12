package com.hanmo.dagger2_mvp_example.ui.repo

import android.util.Log
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.base.BasePresenter
import com.hanmo.dagger2_mvp_example.di.component.DaggerNetworkComponent
import com.hanmo.dagger2_mvp_example.di.module.NetworkModule
import com.hanmo.dagger2_mvp_example.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoPresenter : BasePresenter<RepoView>() {

    @Inject
    lateinit var apiService: ApiService

    private var repoView : RepoView? = null

    val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerNetworkComponent.builder().networkModule(NetworkModule).build().inject(this)
    }

    override fun onViewCreated(view: RepoView) {
        repoView = view
        view.showRefreshing()
        loadRepo()
    }


    fun loadRepo() {
        apiService.getRepo(repoView?.getUserName()!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { repoList ->
                            Log.e("haha", repoList.toString())
                            repoList?.run { repoView?.updateRepo(repoList) }
                                    ?: kotlin.run {  }
                            repoView?.hideRefreshing()
                        }, { repoView?.showError(R.string.error) }
                )
                .apply { compositeDisposable.add(this) }
    }

    override fun onViewDestroyed() {
        repoView = null
        compositeDisposable.clear()
    }
}