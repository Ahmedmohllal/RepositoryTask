package com.example.repositorytask.data.repo

import com.example.repositorytask.data.pojo.contributors.contributor
import com.example.repositorytask.data.pojo.repositorydata.UserRepo
import com.example.repositorytask.data.remote.RemoteApi
import com.example.repositorytask.data.remote.RetrofitModule
import io.reactivex.Observable

class DataRepo(private val api: RemoteApi = RetrofitModule().getClient()) {

    fun getAllRepo() : Observable<UserRepo> {
        return api.fetchRepo()
    }

    fun getAllContributor(repoName : String) : Observable<contributor>{
        return api.fetchContributors(repoName)
    }
}