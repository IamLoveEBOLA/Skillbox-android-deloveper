package com.example.skillbox_hw_quiz.ui.main

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar


/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






        arguments?.let {
            param1 = it.getString(PARAM1)
            param2 = it.getString(PARAM2)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater , container , false)






        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType" , "SimpleDateFormat")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)


        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val calendar = Calendar.getInstance()
        binding.buttonDate.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints)
                .setTitleText(resources.getString(R.string.birth))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis

                Snackbar.make(
                    binding.buttonDate ,
                    dateFormat.format(calendar.time) ,
                    Snackbar.LENGTH_SHORT
                ).show()

            }
            dateDialog.show(parentFragmentManager , "DatePicker")
        }




/*

        ObjectAnimator.ofFloat(
            binding.button ,
            View.ROTATION ,
            360F
        ).apply {
            duration = 10
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = AccelerateInterpolator()
            start()
        }




        ObjectAnimator.ofFloat(
            binding.message ,
            View.ROTATION ,
            360F
        ).apply {
            duration = 10
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = AccelerateInterpolator()
            start()
        }
*/



      /*  ObjectAnimator.ofFloat(
            binding.buttonDate ,
            View.ROTATION ,
            360F
        ).apply {
            duration = 10
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = AccelerateInterpolator()
            start()
        }



        ObjectAnimator.ofFloat(
            binding.welcome ,
            View.ROTATION ,
            360F
        ).apply {
            duration = 10
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = AccelerateInterpolator()
            start()
        }
*/


        /*  binding.message.setOnClickListener{
              binding.message.resources.getFont(R.font.mgs1)
              binding.message.setText(R.string.mgs1)

          }

  */


        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_questionFragment)


        }


        val textShader = LinearGradient(
            0f , 0f ,
            binding.message.paint.measureText(_binding?.message?.text.toString()) ,
            binding.message.textSize ,
            intArrayOf(Color.RED , Color.BLUE , Color.RED) ,
            null ,
            Shader.TileMode.CLAMP

        )
        binding.message.paint.shader = textShader
        binding.message.invalidate()

        /*ValueAnimator.ofObject(
            GradientArgbEvaluator,
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.RED),
            intArrayOf(Color.MAGENTA, Color.BLUE, Color.BLACK),
            intArrayOf(Color.BLACK, Color.GREEN, Color.CYAN),


            ).apply {
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = 3000
            addUpdateListener {
                val shader = LinearGradient(
                    0f, 0f,
                    binding.message.paint.measureText(_binding?.message?.text.toString()) ,
                    binding.message.textSize,
                    it.animatedValue as IntArray,
                    null,
                    Shader.TileMode.CLAMP

                )
                binding.message.paint.shader = shader
                binding.message.invalidate()
            }
            start()



        }*/


    }

    /*object GradientArgbEvaluator : TypeEvaluator<IntArray> {
        private val argbEvaluator = ArgbEvaluator()
        override fun evaluate(fraction: Float, startValue: IntArray, endValue: IntArray): IntArray {
            return startValue.mapIndexed { index, item ->
                argbEvaluator.evaluate(fraction, item, endValue[index]) as Int
            }.toIntArray()
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        const val PARAM1 = "param1"
        const val PARAM2 = "param2"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String , param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM1 , param1)
                    putString(PARAM2 , param2)
                }
            }
    }
}