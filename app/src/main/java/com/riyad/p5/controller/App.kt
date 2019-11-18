package com.riyad.p5.controller

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, AppDatabase::class.java, "notificationDatabase")
            .build()
    }
}