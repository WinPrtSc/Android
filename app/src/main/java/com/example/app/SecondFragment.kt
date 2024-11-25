package com.example.app

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private var count = 0
    private lateinit var counterTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        counterTextView = view.findViewById(R.id.counterTextView)
        val incrementButton: Button = view.findViewById(R.id.incrementButton)

        incrementButton.setOnClickListener {
            count++
            updateCounterText()
            showCustomDialog()
        }

        // Handle back press to return to MainActivity
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        )

        return view
    }

    private fun updateCounterText() {
        counterTextView.text = "Count: $count"
    }

    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Counter Action")
        builder.setMessage("Current count is $count. Choose an action.")

        builder.setPositiveButton("Reset") { dialog, _ ->
            count = 0
            updateCounterText()
            dialog.dismiss()
        }

        builder.setNeutralButton("Show Toast") { dialog, _ ->
            Toast.makeText(requireContext(), "Current count: $count", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}