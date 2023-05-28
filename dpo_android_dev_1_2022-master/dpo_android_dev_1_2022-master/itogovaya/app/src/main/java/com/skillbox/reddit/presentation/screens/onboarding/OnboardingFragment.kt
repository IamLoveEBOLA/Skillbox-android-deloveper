package com.skillbox.reddit.presentation.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.skillbox.reddit.R
import com.skillbox.reddit.databinding.FragmentOnboardingBinding
import com.skillbox.reddit.presentation.ZoomOutPageTransformer
import com.skillbox.reddit.presentation.screens.CommonAbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : CommonAbstractFragment() {
    
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val onboardingAdapter = OnboardingViewPagerAdapter { onSkipClick() }
    
        binding.viewPager.adapter = onboardingAdapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {_,_ -> }.attach()
        
        binding.skipButton.setOnClickListener {
            onSkipClick()
        }
    }
    
    fun onSkipClick() = findNavController().navigate(R.id.from_onboarding_to_auth)

}