package com.example.pennywise.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[Expense::class, SpendingGoal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun goalDao(): GoalDao
}