package com.example.repositorytask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorytask.R
import com.example.repositorytask.pojo.repositorydata.UserRepoItem

class RepoNameAdatper(val listener: OnRepoItemClick) :
    RecyclerView.Adapter<RepoNameAdatper.RepoViewHolder>() {
    var arrayList = ArrayList<UserRepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(v)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.projectName.text = arrayList.get(position).name
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setList(list: ArrayList<UserRepoItem>) {
        arrayList = list
        notifyDataSetChanged()
    }


    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var projectName: TextView = itemView.findViewById(R.id.repo_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition)
            }
        }


    }

    interface OnRepoItemClick {
        fun onItemClick(position: Int)

    }

}
