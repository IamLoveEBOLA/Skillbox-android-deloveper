package com.skillbox.reddit.presentation.screens.subreddits

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skillbox.reddit.presentation.screens.subreddits.NewSubredditsFragment
import com.skillbox.reddit.presentation.screens.subreddits.PopularSubredditsFragment

class SubredditsViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NewSubredditsFragment()
            1 -> PopularSubredditsFragment()
            else -> throw java.lang.IllegalArgumentException("Invalid position $position")
        }
    }
}