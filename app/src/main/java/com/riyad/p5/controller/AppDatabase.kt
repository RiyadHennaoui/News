package com.riyad.p5.controller

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.Notification.NotificationUserInput


@Database(entities = [NotificationUserInput::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao() : NotificationDao

}