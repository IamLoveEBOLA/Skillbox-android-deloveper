package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.data.data_classes.PhotoDto
import com.example.myapplication.data.data_classes.Photos
import com.example.myapplication.databinding.RecyclerViewItemBinding

class CustomAdapter(
    private val photos: Photos ,
    private val onClick: (PhotoDto) -> Unit
):Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): MyViewHolder {
        val binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder , position: Int) {
        val item = photos.photDos[position]
        holder.binding.apply {
            tvCamera.text = item.camera?.name ?: "none"
            tvDate.text = item.earthDate ?: "none"
            tvRover.text = item.rover?.name ?: "none"
            tvSol.text = item.sol.toString()
            var requestOptions = RequestOptions()

            val src = item.imgSrc.toString().replace("http" , "https").replace("jpl." , "")

            Glide.with(ivPhoto.context)
                .load(src)
                .placeholder(R.drawable.photo)
                .into(ivPhoto)
            holder.binding.root.setOnClickListener {
                item?.let { onClick(item) }
            }

        }
    }

    override fun getItemCount(): Int = photos.photDos.size


}