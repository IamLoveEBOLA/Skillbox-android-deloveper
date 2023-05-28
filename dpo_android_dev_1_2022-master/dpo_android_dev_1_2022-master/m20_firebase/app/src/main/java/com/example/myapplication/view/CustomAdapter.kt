package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Photo
import com.example.myapplication.databinding.RecyclerViewItemBinding


class CustomAdapter(
    private val photos:List<Photo> ,
    private val onClick:(Photo)->Unit
):Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MyViewHolder{
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder,position: Int){
        val item = photos[position]
        holder.binding.apply {
            Glide.with(ivPhoto.context)
                .load(item.uri)
                .placeholder(R.drawable.photo)
                .into(ivPhoto)
            tvDate.text = item.date.take(10)
            root.setOnClickListener{
                item?.let{ onClick(item) }
            }
        }
    }

    override fun getItemCount():Int = photos.size
}