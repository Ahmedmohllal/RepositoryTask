package com.example.repositorytask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repositorytask.data.pojo.contributors.contributorItem
import com.example.repositorytask.data.pojo.repositorydata.UserRepoItem
import com.example.repositorytask.data.repo.DataRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel(private val repo: DataRepo = DataRepo()) : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var repoLiveData = MutableLiveData<ArrayList<UserRepoItem>>()
    var contributorLiveData = MutableLiveData<ArrayList<contributorItem>>()

    //fetch data in io-thread and send it to MainThread
    fun fetchAllRepo(){
        compositeDisposable.add(
            DataRepo().getAllRepo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    repoLiveData.value = it
                })

    }

    //fetch all Contributors and send it to MainThread
    fun fetchContributors(repoName : String ){
        compositeDisposable.add(
            DataRepo().getAllContributor(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contributorLiveData.value = it
            })
    }
}