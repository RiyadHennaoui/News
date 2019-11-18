package com.riyad.p5.data.model.Notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationUserInput (
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var inputSearchUser: String,
    var sections: ArrayList<String>
)