package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R

class Question5Fragment : Fragment() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnFinish: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question5, container, false)

        radioGroup = view.findViewById(R.id.radio_group_q5)
        btnFinish = view.findViewById(R.id.btn_finish_q5)

        btnFinish.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(context, "Pilih jawaban terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedAnswer = getAnswerNumber(selectedId)
            (activity as MainActivity).saveAnswer(5, selectedAnswer)

            // Selesai, tampilkan hasil
            (activity as MainActivity).loadFragment(ResultFragment())
        }

        return view
    }

    private fun getAnswerNumber(selectedId: Int): Int {
        return when (selectedId) {
            R.id.radio_q5_a -> 1
            R.id.radio_q5_b -> 2
            R.id.radio_q5_c -> 3
            R.id.radio_q5_d -> 4
            else -> 0
        }
    }
}