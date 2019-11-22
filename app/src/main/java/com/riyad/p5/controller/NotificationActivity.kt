package com.riyad.p5.controller

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.riyad.p5.R
import com.riyad.p5.data.model.Notification.Converters
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.Notification.NotificationUserInput
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class NotificationActivity : AppCompatActivity() {

    private val notificationManager = NotificationManager()


//     val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationSwitch = findViewById<Switch>(R.id.switch1)

        val inputUserNotification = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_view)
        val checkBoxBusiness = findViewById<CheckBox>(R.id.checkbox1)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkbox2)
        val checkBoxThechnology = findViewById<CheckBox>(R.id.checkbox3)
        val checkBoxFood = findViewById<CheckBox>(R.id.checkbox4)

        val notificationDao = App.database.notificationDao()


        notificationSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->

            if (isChecked){

                Log.i("IsChecked", "True")
                //TODO Enregistrer sur le tel pour utilisation ulterieur

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

                        switch1.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        // TODO Les opérations de room doivent être dans un background Thread Observable ou Coroutines


                        CoroutineScope(IO).launch {

                            notificationDao
                                .insertNotificationUserInput(
                                    NotificationUserInput(
                                        inputSearchUser = inputUserNotification.query.toString(),
                                        sections = sections
                                    )
                                )

                            Log.i("ajouter", "fait" + notificationDao.getNotificationUserInput().inputSearchUser)
                        }


                        Log.i("NotificationActivity", notificationDao.toString())


                    }
                }


            }else{
                Log.i("IsChecked", "false")

                CoroutineScope(IO).launch {

                    notificationDao.deleteNotificationInProgress(notificationDao.getNotificationUserInput())
                    Log.i("suppression", "supprimé" )

                }

//                TODO a enlever du tel.

            }


        }

//        notificationSwitch.setOnClickListener {
//
//            Log.i("NotificationActivity", "OnClickSwitch")
//            val sections: ArrayList<String> = ArrayList()
//
//
//            if (checkBoxBusiness.isChecked) sections.add("business")
//            if (checkBoxSports.isChecked) sections.add("sports")
//            if (checkBoxThechnology.isChecked) sections.add("technology")
//            if (checkBoxFood.isChecked) sections.add("food")
//
//            when (notificationManager.checkUserInput(
//                inputUserNotification.query.toString(),
//                sections
//            )) {
//
//                NotificationManager.UserInputState.VALID -> {
//
//                    val converters = Converters.fromArrayList(sections)
//
//                    notificationDao
//                        .insertNotificationUserInput(NotificationUserInput(0,
//                            inputUserNotification.query.toString(),
//                            converters))
//
//                    Log.i("NotificationActivity", notificationDao.toString())
//
//
//                }
//
//                NotificationManager.UserInputState.NO_USER_INPUT -> {
//
//                    Toast.makeText(this, "Merci de replir le champs", Toast.LENGTH_SHORT).show()
//
//                }
//
//                NotificationManager.UserInputState.NO_SECTION_SELECTED -> {
//
//                    Toast.makeText(this, "please selcet Section ",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            }
//
//
//        }
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
