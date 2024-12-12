package com.example.FinalExamApp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.FinalExamApp.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)
}