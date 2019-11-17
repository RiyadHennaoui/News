package com.riyad.p5.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.riyad.p5.R
import com.riyad.p5.data.model.ui.Article
import com.google.gson.reflect.TypeToken
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log


class NotificationActivity : AppCompatActivity() {


     val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val stringSearchResponseResult = intent.getStringExtra("articles")


        val type = object : TypeToken<List<Article>>() { }.type


        val searchResponseResult : List<Article> = gson.fromJson(stringSearchResponseResult, type)

        Log.i("NotificationActivity", searchResponseResult.get(0).title)

        //TODO enregistrer les preferences de notification (sharedpreferences ou room)

        //TODO executer de manière récurante la vérification de ces notifications (workmanger  ou jobScheduler)

    }
}
