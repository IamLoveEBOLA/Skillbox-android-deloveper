package com.example.skillbox_hw_quiz.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.skillbox_hw_quiz.databinding.GroupOneBinding


class CustomView
    @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : LinearLayout(context, attrs, defStyleAttr) {
        val binding = GroupOneBinding.inflate(LayoutInflater.from(context))

        init {
            addView(binding.root)
        }
    }
