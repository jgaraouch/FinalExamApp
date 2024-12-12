package com.example.FinalExamApp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.FinalExamApp.database.AppDatabase
import com.example.FinalExamApp.entities.User
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class CalculatorTest {
    private lateinit var db: AppDatabase
    @Before
    fun setUp() {

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {

        db.close()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun addition_isWorng() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun checkUser()  {

        val userDao = db.userDao()
        val sampleUser = User(id = 1, name = "Test User")
        userDao.insert(sampleUser)

        var users = userDao.getAllUsers()

        assertNotNull(users)
        assertTrue("Database users should not be empty", users?.isNotEmpty() == true)
    }

}