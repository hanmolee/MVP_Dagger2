package com.hanmo.dagger2_mvp_example.ui.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.USER_NAME
import com.hanmo.dagger2_mvp_example.base.BaseActivity
import com.hanmo.dagger2_mvp_example.model.Repo
import kotlinx.android.synthetic.main.activity_repo.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class RepoActivity : BaseActivity(), RepoView {

    @Inject
    lateinit var repoPresenter: RepoPresenter

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        with(repoList) {
            adapter = repoAdapter
        }

        refreshLayout.setOnRefreshListener {
            repoPresenter.loadRepo()
        }

        repoPresenter.onViewCreated(this)
    }

    override fun onDestroy() {
        repoPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun showRefreshing() {
        refreshLayout.isRefreshing = true
    }

    override fun hideRefreshing() {
        refreshLayout.isRefreshing = false
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun updateRepo(repo: List<Repo>) {
        repoAdapter.updateRepo(repo)
    }

    override fun getUserName(): String {
        return intent.getStringExtra(USER_NAME)
    }

    override fun getContext(): Context {
        return this
    }

    companion object {
        fun newIntent(context: Context, userName : String) : Intent {

            val repoIntent = Intent(context, RepoActivity::class.java)
            repoIntent.putExtra(USER_NAME, userName)

            return repoIntent
        }
    }

}