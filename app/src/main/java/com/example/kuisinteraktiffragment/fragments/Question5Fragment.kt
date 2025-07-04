package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R
import com.example.kuisinteraktiffragment.databinding.FragmentQuestionSimpleBinding

class Question5Fragment : Fragment() {
    private var _binding: FragmentQuestionSimpleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupQuestion()
        setupListeners()
        restorePreviousAnswer()
    }

    private fun setupQuestion() {
        binding.tvQuestionNumber.text = "Pertanyaan 5 dari 5"
        binding.progressBar.progress = 5
        binding.tvQuestion.text = "Apa tujuan utama dari visualisasi data?"

        binding.radioOption1.text = "Menyembunyikan data dari pengguna"
        binding.radioOption2.text = "Membuat data terlihat menarik tanpa makna"
        binding.radioOption3.text = "Menyampaikan informasi data dengan cara visual yang mudah dipahami"
        binding.radioOption4.text = "Menyimpan data ke dalam database"

        // Change button text for last question
        binding.btnNext.text = "LIHAT HASIL"
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
                (requireActivity() as MainActivity).answer5 = selectedIndex
                binding.btnNext.isEnabled = true
            }
        }

        binding.btnNext.setOnClickListener {
            // Navigate to ResultFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResultFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun restorePreviousAnswer() {
        val mainActivity = requireActivity() as MainActivity
        val savedAnswer = mainActivity.answer5

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
