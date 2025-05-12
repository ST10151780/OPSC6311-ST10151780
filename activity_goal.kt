package com.example.pennywise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pennywise.data.AppDatabase
import com.example.pennywise.data.GoalDao
import com.example.pennywise.data.SpendingGoal

class activity_goal : AppCompatActivity() {
    private lateinit var goalDao: GoalDao
    private lateinit var db: AppDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)
        
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "pennywise_db").build()
        goalDao = db.goalDao()
        
        val editTextMonth = findViewById<EditText>(R.id.editTextMonth)
        val editTextMin = findViewById<EditText>(R.id.editTextMin)
        val editTextMax = findViewById<EditText>(R.id.editTextMax)
        val btnSave = findViewById<Button>(R.id.btnSaveGoal)
        
        btnSave.setOnClickListener{
            val month = editTextMonth.text.toString()
            val min = editTextMin.text.toString().toDoubleOrNull()
            val max = editTextMax.text.toString().toDoubleOrNull()
            
            if (month.isNotBlank() && min !=null && max !=null){
                val goal = SpendingGoal(month = month, minAmount = min, maxAmount = max)
                Thread {
                    goalDao.insertGoal(goal)
                    runOnUiThread {
                        Toast.makeText(this, "Goal saved", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }.start()
            } else{
                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            }
        }
    }
}