package com.example.repositorytask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.repositorytask.R
import com.example.repositorytask.pojo.contributors.contributorItem

class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ContViewHolder>() {
    var arrayList = ArrayList<contributorItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.contributors_item, parent, false)
        return ContViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContViewHolder, position: Int) {
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

    open class ContViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contributorImage: ImageView = itemView.findViewById(R.id.contributors_avatar)
        var contributorName: TextView = itemView.findViewById(R.id.contributors_name)

    }

}
