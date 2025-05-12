package com.example.pennywise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pennywise.data.AppDatabase
import com.example.pennywise.data.ExpenseDao

class expense_list : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var expenseDao: ExpenseDao
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "pennywise-db").build()
        expenseDao = db.expenseDao()
        val recyclerView = findViewById<RecyclerView>(R.id.expenseRecyclerView)
        val btnFilter = findViewById<Button>(R.id.btnFilter)
        val fromDate = findViewById<EditText>(R.id.fromDate)
        val toDate = findViewById<EditText>(R.id.toDate)

        adapter = ExpenseAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnFilter.setOnClicklistener {
            val from = fromDate.text.toString()
            val to = toDate.text.toString()

            if(from.isNotBlank() && to.isNotBlank()) {
                Thread {
                    val expenses = expenseDao.getExpensesBetween(from.to)
                    runOnUiThread {
                        adapter.submitList(expenses)
                    }
                }.start()
            }
        }
    }
}