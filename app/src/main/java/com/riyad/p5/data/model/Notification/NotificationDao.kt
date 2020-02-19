package com.riyad.p5.data.model.Notification

import androidx.room.*

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotificationUserInput(notificationUserInput: NotificationUserInput)

    @Query("SELECT * FROM NotificationUserInput")
    fun getNotificationUserInput(): NotificationUserInput?

    @Delete
    fun deleteNotificationInProgress(notificationUserInput: NotificationUserInput)
}