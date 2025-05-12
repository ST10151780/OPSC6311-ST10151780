package com.example.pennywise.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: Expense)

    @Query("SELECT * FROM Expense WHERE date BETWEEN :fromDate AND :toDate ORDER BY date ASC")
    fun getExpensesBetween(fromDate: String, toDate: String): List<Expense>

    @Query("""
        SELECT category, SUM(amount) as total
        FROM Expense
        WHERE date BETWEEN :fromDate AND :toDate
        GROUP BY category
        """)
    fun getTotalSpentByCategory(fromDate: String, toDate: String): List<CategoryTotal>
}




