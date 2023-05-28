package com.example.myapplication.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.data_classes.characters.Results
import com.example.myapplication.databinding.RecyclerViewItemBinding

class MyViewHolder(
    val binding: RecyclerViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {}

class CustomAdapter(
    private val onClick:(Results)->Unit
) : PagingDataAdapter<Results , MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): MyViewHolder {
        val binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder , position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            if (item != null) {
                Glide.with(ivAvatar.context)
                    .load(item.image)
                    .placeholder(R.drawable.sample_image)
                    .into(ivAvatar)
            }
            if (item != null) {
                tvName.text = item.name
                tvStatusSpecies.text = "${item.status}-${item.species}"
                tvLocation.text = item.location?.name
            }
            root.setOnClickListener {
                if (item != null) {
                    onClick(item)
                }
            }
        }
    }
}


class DiffUtilCallBack : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results , newItem: Results): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Results , newItem: Results): Boolean =
        oldItem == newItem
}