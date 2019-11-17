package com.riyad.p5.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.riyad.p5.R


class NotificationActivity : AppCompatActivity() {


     val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
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
