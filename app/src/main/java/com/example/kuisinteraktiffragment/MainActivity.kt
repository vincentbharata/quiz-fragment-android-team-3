package com.example.kuisinteraktiffragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.fragments.*

class MainActivity : AppCompatActivity() {

    // Variable untuk menyimpan jawaban user
    private var userAnswers = IntArray(5) // 0=tidak dijawab, 1-4=pilihan A-D

    // Jawaban yang benar (sesuai dengan soal)
    private val correctAnswers = intArrayOf(2, 1, 3, 4, 2) // A=1, B=2, C=3, D=4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tampilkan StartFragment pertama kali
        if (savedInstanceState == null) {
            loadFragment(StartFragment())
        }
    }

    // Method untuk ganti fragment
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // Method untuk menyimpan jawaban user
    fun saveAnswer(questionNumber: Int, selectedAnswer: Int) {
        userAnswers[questionNumber - 1] = selectedAnswer
    }

    // Method untuk mendapatkan jawaban user
    fun getUserAnswer(questionNumber: Int): Int {
        return userAnswers[questionNumber - 1]
    }

    // Method untuk menghitung skor
    fun calculateScore(): Int {
        var score = 0
        for (i in 0 until 5) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++
            }
        }
        return score
    }

    // Method untuk reset kuis
    fun resetQuiz() {
        userAnswers = IntArray(5)
    }

    // Method untuk mendapatkan detail jawaban
    fun getAnswerDetails(): List<Boolean> {
        val details = mutableListOf<Boolean>()
        for (i in 0 until 5) {
            details.add(userAnswers[i] == correctAnswers[i])
        }
        return details
    }
}