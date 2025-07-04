package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R
import com.example.kuisinteraktiffragment.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayResults()
        setupListeners()
    }

    private fun displayResults() {
        val mainActivity = requireActivity() as MainActivity
        val score = mainActivity.calculateScore()
        val totalQuestions = 5

        binding.tvScore.text = "Skor Anda: $score/$totalQuestions"

        val resultMessage = when {
            score == totalQuestions -> {
                binding.ivResultIcon.setImageResource(R.drawable.ic_trophy)
                "Sempurna! Anda menjawab semua pertanyaan dengan benar! 🎉"
            }
            score >= totalQuestions * 0.8 -> {
                binding.ivResultIcon.setImageResource(R.drawable.ic_trophy)
                "Luar biasa! Pengetahuan Anda sangat baik! ⭐"
            }
            score >= totalQuestions * 0.6 -> {
                binding.ivResultIcon.setImageResource(R.drawable.ic_quiz)
                "Bagus! Masih ada ruang untuk belajar lebih lanjut! 📚"
            }
            score >= totalQuestions * 0.4 -> {
                binding.ivResultIcon.setImageResource(R.drawable.ic_quiz)
                "Cukup baik! Terus belajar dan tingkatkan pengetahuan Anda! 💪"
            }
            else -> {
                binding.ivResultIcon.setImageResource(R.drawable.ic_quiz)
                "Jangan menyerah! Coba lagi dan pelajari lebih banyak! 🚀"
            }
        }

        binding.tvResultMessage.text = resultMessage
    }

    private fun setupListeners() {
        binding.btnRestartQuiz.setOnClickListener {
            navigateToStart()
        }

        binding.btnExit.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun navigateToStart() {
        val fragment = StartFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
