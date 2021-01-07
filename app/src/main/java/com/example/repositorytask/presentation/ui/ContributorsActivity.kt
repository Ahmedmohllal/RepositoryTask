package com.example.repositorytask.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorytask.R
import com.example.repositorytask.presentation.adapter.ContributorAdapter
import com.example.repositorytask.data.pojo.contributors.contributorItem
import com.example.repositorytask.presentation.viewmodel.RepoViewModel

class ContributorsActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    lateinit var nameView: TextView
    lateinit var forkView: TextView
    lateinit var watcherView: TextView
    lateinit var repoName: String
    var repoForks = 0
    var repoWatchers = 0
    lateinit var contributorAdapter: ContributorAdapter
    lateinit var contRecyclerView: RecyclerView
    private val repoViewModel by lazy { ViewModelProvider(this).get(RepoViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        //get Repo name and index From RepositoriesActivity
        repoName = intent.getStringExtra("repoName")!!
        repoForks = intent.getIntExtra("repoForkCount", 0)
        repoWatchers = intent.getIntExtra("repoWatcherCount", 0)


        initViews()
        initRecyclerView()
        getAllContributors(repoName)

    }

    private fun initViews() {
        nameView = findViewById(R.id.repoName)
        forkView = findViewById(R.id.repoForks)
        watcherView = findViewById(R.id.repoWatchers)
        progressBar = findViewById(R.id.progress)
        progressBar.visibility = View.VISIBLE
        nameView.text = repoName
        forkView.text = repoForks.toString()
        watcherView.text = repoWatchers.toString()
    }

    //declare RecyclerView
    private fun initRecyclerView(){
        contRecyclerView = findViewById(R.id.contributors_rec)
        contributorAdapter = ContributorAdapter()
        contRecyclerView.adapter = contributorAdapter
        contRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    //observe data from viewModel
    private fun getAllContributors(repoName: String) {
        repoViewModel.fetchContributors (repoName)
        repoViewModel.contributorLiveData.observe(
            this,
            object : Observer<ArrayList<contributorItem>> {
                override fun onChanged(t: ArrayList<contributorItem>?) {
                    contributorAdapter.setList(t!!)
                    progressBar.visibility = View.INVISIBLE
                }

            })

    }


}