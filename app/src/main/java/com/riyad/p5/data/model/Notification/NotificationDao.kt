package com.riyad.p5.data.model.Notification

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotificationUserInput(notificationUserInput: NotificationUserInput)

    @Query("SELECT * FROM NotificationUserInput")
    fun getNotificationUserInput() : NotificationUserInput
}