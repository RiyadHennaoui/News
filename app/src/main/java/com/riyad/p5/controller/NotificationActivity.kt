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


            inputUserNotification.isIconified = false

            CoroutineScope(Dispatchers.Main).launch{

                val recoverDataUserInput = async(IO){recoverDataUserInput()}
                recoverDataUserInput.await()


                notificationSwitch.isChecked = true

            }

        val type = object : TypeToken<List<Article>>() {}.type
        
        if (intent.getStringExtra("articles") != null) {
            val stringSearchResponseResult = intent.getStringExtra("articles")!!
            Log.i("données", stringSearchResponseResult)
            val searchResponseResult: List<Article> =
                gson.fromJson(stringSearchResponseResult, type)

            updateRvNotification(searchResponseResult)
            initRecyclerViewNotification()
        }


    }

    private fun recoverDataUserInput() {
        notificationDao.getNotificationUserInput()?.let {

            Log.i("Coroutine", gson.toJson(it))


            inputUserNotification.setQuery(it.inputSearchUser, false)

            if (it.sections.contains("business")) checkBoxBusiness.isChecked = true
            if (it.sections.contains("food")) checkBoxFood.isChecked = true
            if (it.sections.contains("technology")) checkBoxThechnology.isChecked = true
            if (it.sections.contains("sports")) checkBoxSports.isChecked = true

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
