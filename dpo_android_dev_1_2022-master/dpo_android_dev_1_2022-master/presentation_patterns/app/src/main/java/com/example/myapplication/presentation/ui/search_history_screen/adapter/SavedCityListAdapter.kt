package com.example.myapplication.presentation.ui.search_history_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.databinding.SavedCityListItemBinding

class SavedCityListAdapter(
    private val onClick: (SavedCity) -> Unit
) : ListAdapter<SavedCity , SavedCityListViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): SavedCityListViewHolder {
        return SavedCityListViewHolder(
            SavedCityListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedCityListViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.cityName.text = item.city_name
        holder.binding.date.text = item.date
        holder.binding.region.text = item.region

        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<SavedCity>() {
    override fun areItemsTheSame(oldItem: SavedCity, newItem: SavedCity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SavedCity, newItem: SavedCity): Boolean =
        oldItem == newItem

}