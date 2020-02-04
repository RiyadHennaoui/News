package com.riyad.p5.controller

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox

import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riyad.p5.R
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.Notification.NotificationUserInput
import com.riyad.p5.data.model.ui.Article
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class NotificationActivity : AppCompatActivity() {

    private val notificationManager = NotificationManager()
    private lateinit var adapterNotification: MainAdapter
    private lateinit var mData: List<Article>
    lateinit var rvNotification: RecyclerView
    private lateinit var checkBoxBusiness: CheckBox
    private lateinit var checkBoxThechnology: CheckBox
    private lateinit var checkBoxSports: CheckBox
    private lateinit var checkBoxFood: CheckBox
    private lateinit var inputUserNotification: SearchView
    private lateinit var notificationDao: NotificationDao
    private lateinit var notificationSwitch: Switch


    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        notificationSwitch = findViewById(R.id.switch1)

        inputUserNotification = findViewById(R.id.search_view)
        checkBoxBusiness = findViewById(R.id.checkbox1)
        checkBoxSports = findViewById(R.id.checkbox2)
        checkBoxThechnology = findViewById(R.id.checkbox3)
        checkBoxFood = findViewById(R.id.checkbox4)

        notificationDao = App.database.notificationDao()
        rvNotification = findViewById(R.id.rv_notification_article)


        setSupportActionBar(findViewById(R.id.toolbar_notification))
        supportActionBar!!.title = "Notification"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


//TODO récupérer les préférences de notifications déjà enregistrer sur Room


        //TODO trouver comment afficher le texte de la dernière recherche utilisateur

           // Log.i("DAO recp", notificationDao.getNotificationUserInput().toString())
            inputUserNotification.isIconified = false

            CoroutineScope(Dispatchers.Main).launch{

                val recoverDataUserInput = async(IO){recoverDataUserInput()}
                recoverDataUserInput.await()


                notificationSwitch.isChecked = true

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

//
//
        val type = object : TypeToken<List<Article>>() {}.type
//
        if (intent.getStringExtra("articles") != null) {
            val stringSearchResponseResult = intent.getStringExtra("articles")!!
            Log.i("données", stringSearchResponseResult)
            val searchResponseResult: List<Article> =
                gson.fromJson(stringSearchResponseResult, type)

            updateRvNotification(searchResponseResult)
            initRecyclerViewNotification()
        }
//        Log.i("NotificationActivity", searchResponseResult.get(0).title)

        //TODO faire les tests unitaires en premier. s'inspiré de SearchActivity.

        //TODO enregistrer les preferences de notification (sharedpreferences ou room)

        //TODO executer de manière récurante la vérification de ces notifications (workmanger  ou jobScheduler)

        //TODO enregistrer lorsque l'utilsateur click sur le précedent du toolbar ou celui du téléphone

        //TODO Docs sur les tests instrumentalisés

    }

    private fun recoverDataUserInput() {
        notificationDao.getNotificationUserInput()?.let {

            Log.i("Coroutine", gson.toJson(it))


            inputUserNotification.setQuery(it.inputSearchUser, false)

            if (it.sections.contains("business")) checkBoxBusiness.isChecked = true
            if (it.sections.contains("food")) checkBoxFood.isChecked = true
            if (it.sections.contains("technology")) checkBoxThechnology.isChecked = true
            if (it.sections.contains("sports")) checkBoxSports.isChecked = true

            //TODO revoir le fonctionnement des coroutines!
            //TODO Lire https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md
            //  notificationSwitch.isChecked = true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {

            save()
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun save() {

        if (notificationSwitch.isChecked) {

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
//TODO Ajouter les 2 autres enum



                NotificationManager.UserInputState.NO_USER_INPUT -> {

                    Toast.makeText(this, "Merci de replir le champs", Toast.LENGTH_SHORT).show()

                }

                NotificationManager.UserInputState.NO_SECTION_SELECTED -> {

                    Toast.makeText(this, "please selcet Section ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

                        Log.i(
                            "ajouter",
                            "fait" + notificationDao.getNotificationUserInput()!!.inputSearchUser
                        )
                        Log.i(
                            "NotificationActivity",
                            notificationDao.getNotificationUserInput()!!.sections.get(0)
                        )

                    }

//                        val work = OneTimeWorkRequestBuilder<SyncNotificationWorker>()
                    val work =
                        PeriodicWorkRequestBuilder<SyncNotificationWorker>(1, TimeUnit.DAYS)
                            .build()
                    WorkManager.getInstance(this).enqueue(work)


                }
            }


        } else {
            Log.i("IsChecked", "false")

            CoroutineScope(IO).launch {

                notificationDao.deleteNotificationInProgress(notificationDao.getNotificationUserInput()!!)
                Log.i("suppression", "supprimé")

            }

//                TODO a enlever du tel.

        }
    }

    private fun updateRvNotification(notificationResult: List<Article>) {

        mData = notificationResult
        adapterNotification = MainAdapter(this)
        adapterNotification.setData(notificationResult)
        val gson = Gson()

        Log.i("List d'article", gson.toJson(mData))

    }

    private fun initRecyclerViewNotification() {

        rvNotification.apply {

            layoutManager = LinearLayoutManager(this@NotificationActivity)
            adapter = adapterNotification
        }

    }

    override fun onBackPressed() {

        save()

        super.onBackPressed()
    }
}
