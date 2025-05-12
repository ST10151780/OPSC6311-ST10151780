package com.example.pennywise.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpendingGoal (
    @PrimaryKey val month: String,
    val minAmount: Double,
    val maxAmount: Double
)
