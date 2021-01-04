package com.example.repositorytask.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorytask.R
import com.example.repositorytask.presentation.adapter.RepoAdatper
import com.example.repositorytask.data.pojo.repositorydata.UserRepoItem
import com.example.repositorytask.presentation.viewmodel.RepoViewModel

class RepositoriesActivity : AppCompatActivity(), RepoAdatper.OnRepoItemClick {
    lateinit var repoRec: RecyclerView
    lateinit var repoAdatper: RepoAdatper
    private lateinit var repoList: ArrayList<UserRepoItem>
    private val repoViewModel by lazy { ViewModelProvider(this).get(RepoViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        getAllRepo()
    }

    private fun initRecyclerView(){
        repoRec = findViewById(R.id.repo_rec)
        repoAdatper = RepoAdatper(this)
        repoRec.adapter = repoAdatper
        repoRec.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repoRec.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

    //observe data from viewModel
    private fun getAllRepo() {
        repoViewModel.fetchAllRepo()
        repoViewModel.repoLiveData.observe(this, object : Observer<ArrayList<UserRepoItem>> {
            override fun onChanged(t: ArrayList<UserRepoItem>?) {
                repoList = ArrayList<UserRepoItem>()
                repoList = t!!
                repoAdatper.setList(repoList)
            }
        })
    }

    override fun onItemClick(position: Int) {
        var repoName = repoList[position].name
        var repoForkCount = repoList[position].forksCount
        var repoWatcherCount = repoList[position].watchersCount

        //send data to details activity
        val intent = Intent(this, ContributorsActivity::class.java)
        intent.putExtra("repoName", repoName)
        intent.putExtra("repoForkCount", repoForkCount)
        intent.putExtra("repoWatcherCount", repoWatcherCount)
        startActivity(intent)
    }
}