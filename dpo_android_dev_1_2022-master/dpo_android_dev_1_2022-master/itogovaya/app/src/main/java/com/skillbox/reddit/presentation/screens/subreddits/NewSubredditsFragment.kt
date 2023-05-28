package com.skillbox.reddit.presentation.screens.subreddits

import androidx.fragment.app.viewModels
import com.skillbox.reddit.presentation.screens.AbstractSubredditsFragment
import com.skillbox.reddit.presentation.viewModels.subreddits.NewSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewSubredditsFragment : AbstractSubredditsFragment() {
    
    override val request = "new"
    
    override val viewModel: NewSubredditsViewModel by viewModels()
    
}