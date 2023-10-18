package com.andromeda.latihanuts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private var listMovie : List<MovieModel>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById<TextView>(R.id.movie_title)
        val desc: TextView = view.findViewById<TextView>(R.id.movie_desc)
        val thumbnail: ImageView = view.findViewById<ImageView>(R.id.movie_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listMovie[position].title
        holder.desc.text = listMovie[position].description

        Glide.with(holder.thumbnail)
            .load(listMovie[position].thumbnail)
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return 10
    }
}