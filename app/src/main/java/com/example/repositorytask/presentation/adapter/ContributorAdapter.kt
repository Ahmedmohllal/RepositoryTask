package com.example.repositorytask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.repositorytask.R
import com.example.repositorytask.data.pojo.contributors.contributorItem

class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ContributorViewhHolder>() {
    var arrayList = ArrayList<contributorItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewhHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.contributors_item, parent, false)
        return ContributorViewhHolder(v)
    }

    override fun onBindViewHolder(holder: ContributorViewhHolder, position: Int) {
        holder.contributorName.text = arrayList[position].login
        //download image
        var imgUrl: String = arrayList[position].avatarUrl
        Glide
            .with(holder.contributorImage.context)
            .load(imgUrl)
            .into(holder.contributorImage)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setList(list: ArrayList<contributorItem>) {
        arrayList = list
        notifyDataSetChanged()
    }

    open class ContributorViewhHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contributorImage: ImageView = itemView.findViewById(R.id.contributors_avatar)
        var contributorName: TextView = itemView.findViewById(R.id.contributors_name)

    }

}
