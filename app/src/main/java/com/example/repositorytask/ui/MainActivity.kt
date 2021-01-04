package com.example.repositorytask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorytask.R
import com.example.repositorytask.adapter.RepoNameAdatper
import com.example.repositorytask.pojo.repositorydata.UserRepoItem
import com.example.repositorytask.viewmodel.RepoViewModel

class MainActivity : AppCompatActivity(), RepoNameAdatper.OnRepoItemClick {
    lateinit var repoRec: RecyclerView
    lateinit var repoNameAdatper: RepoNameAdatper
    lateinit var repoList: ArrayList<UserRepoItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recyclerview
        repoRec = findViewById(R.id.repo_rec)
        repoNameAdatper = RepoNameAdatper(this)
        repoRec.adapter = repoNameAdatper
        repoRec.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repoRec.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))



        getAllRepo()
    }

    //observe data from viewModel
    private fun getAllRepo() {
        val repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        repoViewModel.fetchData()
        repoViewModel.mutableLiveData.observe(this, object : Observer<ArrayList<UserRepoItem>> {
            override fun onChanged(t: ArrayList<UserRepoItem>?) {
                repoList = ArrayList<UserRepoItem>()
                repoList = t!!
                repoNameAdatper.setList(repoList)
            }
        })
    }

    override fun onItemClick(position: Int) {
        var repoName = repoList[position].name
        var repoForkCount = repoList[position].forksCount
        var repoWatcherCount = repoList[position].watchersCount

        //send data to details activity
        val intent = Intent(this, RepoDetails::class.java)
        intent.putExtra("repoName", repoName)
        intent.putExtra("repoForkCount", repoForkCount)
        intent.putExtra("repoWatcherCount", repoWatcherCount)
        startActivity(intent)
        repoNameAdatper.notifyDataSetChanged()
    }
}