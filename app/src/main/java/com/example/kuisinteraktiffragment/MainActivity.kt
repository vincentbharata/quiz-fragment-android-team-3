package com.example.kuisinteraktiffragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kuisinteraktiffragment.databinding.ActivityMainBinding
import com.example.kuisinteraktiffragment.fragments.StartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Simple variables to store user answers as required
    var answer1: Int = -1  // Question 1 answer (0-3, -1 = not answered)
    var answer2: Int = -1  // Question 2 answer
    var answer3: Int = -1  // Question 3 answer
    var answer4: Int = -1  // Question 4 answer
    var answer5: Int = -1  // Question 5 answer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load StartFragment initially
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StartFragment())
                .commit()
        }
    }

    // Method to reset all answers
    fun resetAnswers() {
        answer1 = -1
        answer2 = -1
        answer3 = -1
        answer4 = -1
        answer5 = -1
    }

    // Method to calculate score
    fun calculateScore(): Int {
        var score = 0

        // Correct answers for each question (0-3)
        val correctAnswers = arrayOf(2, 1, 2, 1, 2)

        if (answer1 == correctAnswers[0]) score++
        if (answer2 == correctAnswers[1]) score++
        if (answer3 == correctAnswers[2]) score++
        if (answer4 == correctAnswers[3]) score++
        if (answer5 == correctAnswers[4]) score++

        return score
    }
}