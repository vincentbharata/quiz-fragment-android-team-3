package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R
import com.example.kuisinteraktiffragment.databinding.FragmentQuestionBinding

class Question1Fragment : Fragment() {
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
        binding.tvQuestionNumber.text = "Pertanyaan 1 dari 5"
        binding.progressBar.progress = 1
        binding.tvQuestion.text = "Fungsi utama dari SQL (Structured Query Language) adalah...?"

        binding.radioOption1.text = "Mendesain tampilan halaman web"
        binding.radioOption2.text = "Mengedit gambar"
        binding.radioOption3.text = "Mengelola dan mengambil data dari database"
        binding.radioOption4.text = "Membuat animasi"
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
                (requireActivity() as MainActivity).answer1 = selectedIndex
                binding.btnNext.isEnabled = true
            }
        }

        binding.btnNext.setOnClickListener {
            // Navigate to Question2Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Question2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun restorePreviousAnswer() {
        val mainActivity = requireActivity() as MainActivity
        val savedAnswer = mainActivity.answer1

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
