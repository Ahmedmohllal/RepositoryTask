package com.example.repositorytask.repo

import com.example.repositorytask.pojo.contributors.contributor
import com.example.repositorytask.pojo.repositorydata.UserRepo
import com.example.repositorytask.remote.RetrofitModule
import io.reactivex.Observable

class DataRepo {

    fun getAllRepo() : Observable<UserRepo> {
        return RetrofitModule().getClient().fetchRepo()
    }

    fun getAllContributor(repoName : String) : Observable<contributor>{
        return RetrofitModule().getClient().fetchContributors(repoName)
    }
}