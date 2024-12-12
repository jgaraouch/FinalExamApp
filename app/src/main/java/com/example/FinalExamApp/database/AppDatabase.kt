package com.example.FinalExamApp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.FinalExamApp.dao.UserDao
import com.example.FinalExamApp.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
 