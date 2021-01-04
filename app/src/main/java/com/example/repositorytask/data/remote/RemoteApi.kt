package com.example.repositorytask.data.remote

import com.example.repositorytask.data.pojo.contributors.contributor
import com.example.repositorytask.data.pojo.repositorydata.UserRepo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApi {

    @GET("users/romannurik/repos")
    fun fetchRepo() : Observable<UserRepo>

    @GET("repos/romannurik/{RepoName}/contributors")
    fun fetchContributors(@Path("RepoName") repoName : String) : Observable<contributor>
}