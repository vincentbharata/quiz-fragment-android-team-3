package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R
import com.example.kuisinteraktiffragment.databinding.FragmentQuestionBinding

class Question4Fragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupQuestion()
        setupListeners()
        restorePreviousAnswer()
    }

    private fun setupQuestion() {
        binding.tvQuestionNumber.text = "Pertanyaan 4 dari 5"
        binding.progressBar.progress = 4
        binding.tvQuestion.text = "Di Python, library apa yang umum digunakan untuk analisis data?"

        binding.radioOption1.text = "React"
        binding.radioOption2.text = "Pandas"
        binding.radioOption3.text = "Django"
        binding.radioOption4.text = "Bootstrap"
    }

    private fun setupListeners() {
        binding.radioGroupOptions.setOnCheckedChangeListener { _, checkedId ->
            val selectedIndex = when (checkedId) {
                binding.radioOption1.id -> 0
                binding.radioOption2.id -> 1
                binding.radioOption3.id -> 2
                binding.radioOption4.id -> 3
                else -> -1
            }

            if (selectedIndex != -1) {
                // Save answer in MainActivity
                (requireActivity() as MainActivity).answer4 = selectedIndex
                binding.btnNext.isEnabled = true
            }
        }

        binding.btnNext.setOnClickListener {
            // Navigate to Question5Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Question5Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun restorePreviousAnswer() {
        val mainActivity = requireActivity() as MainActivity
        val savedAnswer = mainActivity.answer4

        if (savedAnswer != -1) {
            val radioButton = when (savedAnswer) {
                0 -> binding.radioOption1
                1 -> binding.radioOption2
                2 -> binding.radioOption3
                3 -> binding.radioOption4
                else -> null
            }
            radioButton?.isChecked = true
            binding.btnNext.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
