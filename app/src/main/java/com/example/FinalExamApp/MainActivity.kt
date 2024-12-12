package com.example.FinalExamApp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.FinalExamApp.database.AppDatabase
import com.example.FinalExamApp.entities.User
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(/* view = */ findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("MainActivity", "Application démarrée")

        Toast.makeText(this, "Bienvenue sur FinalExamApp!", Toast.LENGTH_LONG).show()
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, R.string.welcome_message, Snackbar.LENGTH_SHORT).show()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "exam_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Exam Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Notification FinalExamApp")
            .setContentText("Bonjour, voici un exemple de notification.")
            .setSmallIcon(R.drawable.ic_notification)
            .build()
        notificationManager.notify(1, notification)
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(this).enqueue(workRequest)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val userDao = db.userDao()


        userDao.getAll().observe(this, Observer { users ->
            users.forEach { user ->
                Log.d("ListDebug", "Item: ${user.name}")

            }
        })
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        editor.putString("username", "JihedGaraouch")
        editor.putInt("userId", 11)
        editor.apply()

        val username = sharedPreferences.getString("username", "JihedGaraouch")
        val userId = sharedPreferences.getInt("userId", -1)
        val displayTextView = findViewById<TextView>(R.id.displayTextView)
        if(userId ==null) {
            Log.e("User","user Id is NUll")
        }
        displayTextView.text = "Username: $username, UserId: $userId"

    }
}