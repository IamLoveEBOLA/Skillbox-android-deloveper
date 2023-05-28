package com.skillbox.reddit.presentation.screens.favourite

import androidx.fragment.app.viewModels
import com.skillbox.reddit.presentation.screens.AbstractSubredditsFragment
import com.skillbox.reddit.presentation.viewModels.favourite.FavoriteSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteSubredditsFragment : AbstractSubredditsFragment() {
    
    override val request = ""
    
    override val viewModel: FavoriteSubredditsViewModel by viewModels()
}