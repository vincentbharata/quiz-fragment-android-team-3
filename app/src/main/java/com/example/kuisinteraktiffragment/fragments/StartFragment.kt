package com.example.kuisinteraktiffragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kuisinteraktiffragment.MainActivity
import com.example.kuisinteraktiffragment.R

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val btnStart = view.findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            // Reset kuis dan mulai dari soal 1
            (activity as MainActivity).resetQuiz()
            (activity as MainActivity).loadFragment(Question1Fragment())
        }

        return view
    }
}