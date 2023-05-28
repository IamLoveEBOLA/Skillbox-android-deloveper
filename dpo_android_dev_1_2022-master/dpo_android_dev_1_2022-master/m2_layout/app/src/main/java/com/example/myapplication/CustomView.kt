package com.example.myapplication
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.myapplication.databinding.MyCustomviewBinding



class CustomView
    @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : LinearLayout(context,attrs,defStyleAttr) {
    private val binding : MyCustomviewBinding

    init {

        val root = inflate(context, R.layout.my_customview, this)

        binding = MyCustomviewBinding.bind(root)

    }
    fun setUp(text : String) {binding.Up.text = text}
    fun setLow(text : String) {binding.Low.text = text}


}