package com.example.pennywise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoal(goal: SpendingGoal)

    @Query("SELECT * FROM SpendingGoal WHERE month = :month LIMIT 1")
    fun getGoalForMonth(month: String): SpendingGoal
}