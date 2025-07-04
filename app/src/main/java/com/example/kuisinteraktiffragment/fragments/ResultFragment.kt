package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R

class ResultFragment : Fragment() {

    private lateinit var tvScore: TextView
    private lateinit var tvMessage: TextView
    private lateinit var tvDetails: TextView
    private lateinit var btnRestart: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        tvScore = view.findViewById(R.id.tv_score)
        tvMessage = view.findViewById(R.id.tv_message)
        tvDetails = view.findViewById(R.id.tv_details)
        btnRestart = view.findViewById(R.id.btn_restart)

        // Hitung dan tampilkan skor
        val score = (activity as MainActivity).calculateScore()
        val percentage = (score * 100) / 5

        tvScore.text = "Skor Anda: $score / 5 ($percentage%)"

        // Pesan berdasarkan skor
        tvMessage.text = when {
            score == 5 -> "🎉 Sempurna! Anda menguasai materi dengan baik!"
            score >= 4 -> "👏 Bagus sekali! Hampir sempurna!"
            score >= 3 -> "👍 Lumayan! Masih bisa diperbaiki."
            score >= 2 -> "😊 Cukup, tapi perlu belajar lagi."
            else -> "😅 Yuk belajar lagi untuk hasil yang lebih baik!"
        }

        // Detail jawaban
        val details = (activity as MainActivity).getAnswerDetails()
        val detailText = StringBuilder("Detail Jawaban:\n")
        for (i in details.indices) {
            val status = if (details[i]) "✓" else "✗"
            detailText.append("Soal ${i + 1}: $status\n")
        }
        tvDetails.text = detailText.toString()

        btnRestart.setOnClickListener {
            (activity as MainActivity).loadFragment(StartFragment())
        }

        return view
    }
}