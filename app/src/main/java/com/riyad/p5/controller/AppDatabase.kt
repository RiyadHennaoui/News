package com.riyad.p5.controller

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.riyad.p5.data.model.Notification.Converters
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.Notification.NotificationUserInput


@Database(entities = [NotificationUserInput::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}