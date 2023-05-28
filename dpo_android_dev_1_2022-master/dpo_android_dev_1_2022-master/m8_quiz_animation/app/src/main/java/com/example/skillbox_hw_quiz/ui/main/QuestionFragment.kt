package com.example.skillbox_hw_quiz.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.RadioButton
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController

import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuestionBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */




class QuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(PARAM1)
            param2 = it.getString(PARAM2)
        }

    }


    private val questionList = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions

    private val answers = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBinding.inflate(inflater,container,false)

        getQuestionGroup()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        ObjectAnimator.ofFloat(binding.scroll,
            View.ALPHA,
            1f


        ).apply {

            duration = 1500
            interpolator = AccelerateInterpolator()
            start()
        }



        binding.sendButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString(
                    PARAM1,
                    QuizStorage.answer(QuizStorage.getQuiz(QuizStorage.Locale.Ru), answers),

                )
                putString(
                    PARAM1,
                QuizStorage.answer(QuizStorage.getQuiz(QuizStorage.Locale.En), answers)
                )
            }
            findNavController().navigate( R.id.action_questionFragment_to_resultFragment, args = bundle)

        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_welcomeFragment)

        }
    }


    private fun getQuestionGroup() {

        questionList.forEach {
            val group = CustomView(requireContext())
            group.binding.qText.text = it.question
            it.answers.forEachIndexed { index, text ->
                val radioButton = RadioButton(context)
                radioButton.id = index
                radioButton.text = text
                radioButton.textSize = 20.0F
                group.binding.rGroup.addView(radioButton)
                group.binding.rGroup.setOnCheckedChangeListener { _, _ ->
                    if (group.binding.rGroup.checkedRadioButtonId >= 0) {
                        answers.add(group.binding.rGroup.checkedRadioButtonId)
                    } else answers.add(-1)
                }
            }
            binding.quiz.addView(group)
        }

    }

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
         * @return A new instance of fragment QuestionFragment.
         */
        const val PARAM1 = "param1"
        const val PARAM2 = "param2"
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putString(PARAM1, param1)
                putString(PARAM2, param2)
            }
        }
    }
}