package com.example.repositorytask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repositorytask.pojo.contributors.contributorItem
import com.example.repositorytask.pojo.repositorydata.UserRepoItem
import com.example.repositorytask.repo.DataRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var mutableLiveData = MutableLiveData<ArrayList<UserRepoItem>>()
    var contributorLiveData = MutableLiveData<ArrayList<contributorItem>>()

    //fetch data in io-thread and send it to MainThread
    fun fetchData(){
        compositeDisposable.add(DataRepo().getAllRepo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mutableLiveData.value = it
                })

    }

    //fetch all Contributors and send it to MainThread
    fun fetchContributor(repoName : String ){
        compositeDisposable.add(DataRepo().getAllContributor(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contributorLiveData.value = it
            })
    }
}