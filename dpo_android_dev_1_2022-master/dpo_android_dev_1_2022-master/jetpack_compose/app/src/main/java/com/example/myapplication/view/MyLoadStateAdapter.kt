package com.example.myapplication.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LoadStateAdapterBinding

class MyLoadStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder , loadState: LoadState) = Unit

    override fun onCreateViewHolder(parent: ViewGroup , loadState: LoadState): LoadStateViewHolder {
        Log.d("MyLod",loadState.toString())
        val binding = LoadStateAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)
    }


}
class LoadStateViewHolder(binding: LoadStateAdapterBinding): RecyclerView.ViewHolder(binding.root)