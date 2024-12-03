package com.example.app

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val names = mutableListOf<String>()
    private lateinit var nameAdapter: NameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        nameAdapter = NameAdapter(
            names,
            onItemClick = { position -> showDeleteDialog(position) },
            onItemLongClick = { position -> showEditDialog(position) }
        )
        binding.recyclerViewNames.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = nameAdapter
        }
    }

    private fun setupAddButton() {
        binding.btnAddName.setOnClickListener {
            val newName = binding.etName.text.toString().trim()
            if (newName.isNotEmpty()) {
                names.add(newName)
                nameAdapter.notifyItemInserted(names.size - 1)
                binding.etName.text.clear()
            }
        }
    }

    private fun showDeleteDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("이름 목록 삭제하기")
            .setMessage("이름 목록을 삭제해보자.")
            .setPositiveButton("삭제") { _, _ ->
                names.removeAt(position)
                nameAdapter.notifyItemRemoved(position)
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun showEditDialog(position: Int) {
        val editText = EditText(this).apply {
            hint = "수정할 이름을 입력하세요."
            setText(names[position])
        }

        AlertDialog.Builder(this)
            .setView(editText)
            .setPositiveButton("확인") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    names[position] = newName
                    nameAdapter.notifyItemChanged(position)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }
}