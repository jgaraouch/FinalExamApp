package com.example.FinalExamApp
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("MyWorker", "Tâche en arrière-plan exécutée")
        return Result.success()
    }
}