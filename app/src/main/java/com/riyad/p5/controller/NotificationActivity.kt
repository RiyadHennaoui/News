package com.riyad.p5.controller

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.riyad.p5.R
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.Notification.NotificationUserInput


class NotificationActivity : AppCompatActivity() {

    private val notificationManager = NotificationManager()


//     val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationSwitch = findViewById<Switch>(R.id.switch1)

        val inputUserNotification = findViewById<SearchView>(R.id.search_view)
        val checkBoxBusiness = findViewById<CheckBox>(R.id.checkbox1)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkbox2)
        val checkBoxThechnology = findViewById<CheckBox>(R.id.checkbox3)
        val checkBoxFood = findViewById<CheckBox>(R.id.checkbox4)

        val notificationDao = App.database.notificationDao()

        notificationSwitch.setOnClickListener {

            val sections: ArrayList<String> = ArrayList()


            if (checkBoxBusiness.isChecked) sections.add("business")
            if (checkBoxSports.isChecked) sections.add("sports")
            if (checkBoxThechnology.isChecked) sections.add("technology")
            if (checkBoxFood.isChecked) sections.add("food")

            when (notificationManager.checkUserInput(
                inputUserNotification.query.toString(),
                sections
            )) {

                NotificationManager.UserInputState.VALID -> {

                    notificationDao
                        .insertNotificationUserInput(NotificationUserInput(0,
                            inputUserNotification.query.toString(),
                            sections))

                    Log.i("NotificationActivity", notificationDao.toString())


                }

                NotificationManager.UserInputState.NO_USER_INPUT -> {

                    Toast.makeText(this, "Merci de replir le champs", Toast.LENGTH_SHORT).show()

                }

                NotificationManager.UserInputState.NO_SECTION_SELECTED -> {

                    Toast.makeText(this, "please selcet Section ",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }
//
//        val stringSearchResponseResult = intent.getStringExtra("articles")
//
//
//        val type = object : TypeToken<List<Article>>() { }.type
//
//
//        val searchResponseResult : List<Article> = gson.fromJson(stringSearchResponseResult, type)
//
//        Log.i("NotificationActivity", searchResponseResult.get(0).title)

        //TODO faire les tests unitaires en premier. s'inspiré de SearchActivity.

        //TODO enregistrer les preferences de notification (sharedpreferences ou room)

        //TODO executer de manière récurante la vérification de ces notifications (workmanger  ou jobScheduler)

        //TODO enregistrer lorsque l'utilsateur click sur le précedent du toolbar ou celui du téléphone

        //TODO Docs sur les tests instrumentalisés

    }
}
