package com.example.pennywise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pennywise.data.AppDatabase
import com.example.pennywise.data.ExpenseDao

class category_report : AppCompatActivity() {

    private lateinit var  db: AppDatabase
    private lateinit var expenseDao: ExpenseDao
    private lateinit var adapter: CategoryReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_report)

        db =Room.databaseBuilder(applicationContext, AppDatabase::class.java, "pennywise-db").build()
        expenseDao= db.expenseDao()

        val editFrom = findViewById<EditText>(R.id.editFrom)
        val editTo = findViewById<EditText>(R.id.editTo)
        val btnGenerate = findViewById<Button>(R.id.btnGenerateReport)
        val reportView = findViewById<RecyclerView>(R.id.reportRecyclerView)

        adapter = CategoryReportAdapter()
        reportView.layoutManager = LinearLayoutManager(this)
        reportView.adapter

        btnGenerate.setOnClickListener {
            val from = editFrom.text.toString()
            val to = editTo.text.toString()

            if (from.isNotBlank() && to.isNotBlank()) {
                Thread {
                    val report = expenseDao.getTotalSpentByCategory(from,to)
                    runOnUiThread{
                        adapter.submitList(report)
                    }
                }.start()
            }
        }
    }
}