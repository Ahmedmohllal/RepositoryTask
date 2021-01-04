package com.example.repositorytask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorytask.R
import com.example.repositorytask.adapter.ContributorAdapter
import com.example.repositorytask.pojo.contributors.contributorItem
import com.example.repositorytask.viewmodel.RepoViewModel

class RepoDetails : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var forkView: TextView
    lateinit var watcherView: TextView
    lateinit var repoName: String
    lateinit var contributorAdapter: ContributorAdapter
    lateinit var contRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        //get Repo name and index From MainActivity
        repoName = intent.getStringExtra("repoName")!!
        val repoForks = intent.getIntExtra("repoForkCount", 0)
        val repoWatchers = intent.getIntExtra("repoWatcherCount", 0)


        //views
        contRecyclerView = findViewById(R.id.contributors_rec)
        contributorAdapter = ContributorAdapter()
        contRecyclerView.adapter = contributorAdapter
        contRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        contRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        //other views
        nameView = findViewById(R.id.repoName)
        forkView = findViewById(R.id.repoForks)
        watcherView = findViewById(R.id.repoWatchers)
        nameView.text = repoName
        forkView.text = repoForks.toString()
        watcherView.text = repoWatchers.toString()


        getAllContributor(repoName)

    }

    //observe data from viewModel
    private fun getAllContributor(repoName: String) {
        val repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        repoViewModel.fetchContributor(repoName)
        repoViewModel.contributorLiveData.observe(
            this,
            object : Observer<ArrayList<contributorItem>> {
                override fun onChanged(t: ArrayList<contributorItem>?) {
                    contributorAdapter.setList(t!!)
                }

            })

    }


}