package com.example.myapplication.presentation.ui.city_search_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.databinding.CityListItemBinding


class CityListAdapter(
    private val onClick: (CityListItem) -> Unit
) : ListAdapter<CityListItem , CityListViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): CityListViewHolder {
        return CityListViewHolder(
            CityListItemBinding.inflate(
                LayoutInflater.from(parent.context) , parent , false
            )
        )
    }

    override fun onBindViewHolder(holder: CityListViewHolder , position: Int) {
        val item = getItem(position)
        holder.binding.cityName.text = item.localizedName
        holder.binding.region.text = item.administrativeArea.localizedName

        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<CityListItem>() {
    override fun areItemsTheSame(oldItem: CityListItem , newItem: CityListItem): Boolean =
        oldItem.localizedName == newItem.localizedName

    override fun areContentsTheSame(oldItem: CityListItem , newItem: CityListItem): Boolean =
        oldItem == newItem

}

