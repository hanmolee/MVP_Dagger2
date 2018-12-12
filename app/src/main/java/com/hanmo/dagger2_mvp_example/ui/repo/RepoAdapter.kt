package com.hanmo.dagger2_mvp_example.ui.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanmo.dagger2_mvp_example.R
import com.hanmo.dagger2_mvp_example.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoHolder>() {

    private var repo : List<Repo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false);
        return RepoHolder(view)
    }

    override fun getItemCount(): Int {
        return repo.size
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(repo[position])
    }

    fun updateRepo(repo: List<Repo>) {
        this.repo = repo
        notifyDataSetChanged()
    }

    class RepoHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo) {
            view.repoNameText.text = repo.name
            view.repoDescriptionText.text = repo.description
            view.repoStarText.text = repo.stargazers_count.toString()
        }
    }

}