package com.riyad.p5.controller

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.riyad.p5.R
import com.riyad.p5.data.model.Notification.NotificationDao
import com.riyad.p5.data.model.search.SearchResponse
import com.riyad.p5.data.model.search.SearchResult
import com.riyad.p5.data.model.search.mapSearchResponseDataToSearchResult
import com.riyad.p5.data.model.ui.Article
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val NOTIFICATION_ID = 0

class SyncNotificationWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {

        Log.i("Worker", "Work !!")
        val dateFormatter = org.threeten.bp.format.DateTimeFormatter.BASIC_ISO_DATE
        val date = LocalDate.now()

        val notificationDate = date.format(dateFormatter).toString()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serviceNotificationSearch = retrofit.create(NewYorkTimesAPI::class.java)

        val notificationDao = App.database.notificationDao().getNotificationUserInput()

        var paramFilter = "news_desk:("

        notificationDao!!.sections.forEach {
            paramFilter += "\"$it\" "
        }
        paramFilter += ")"
        val callNotificationSearch = serviceNotificationSearch
            .getSearchResponse(
                notificationDao.inputSearchUser,
                paramFilter, notificationDate,
                notificationDate,
                "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5"
            )

        callNotificationSearch.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(
                call: Call<SearchResponse>,
                t: Throwable
            ) {

                Log.w("SerchActivity onFailure", t.message, t)

            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {

                Log.e("OnResponse", "???")
                response.body()?.let {

                    val gson = Gson()
                    Log.i(
                        "SyncWorkerResponse",
                        "Yeahhh !! : ${gson.toJson(it)}  "
                    )
                    val searchNotificationResult = mapSearchResponseDataToSearchResult(it)


//TODO afficher une notification avec in intent et l'intent va contenir la liste d'article.

                    if (it.response.docs.isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            "Aucun Article Trouvé",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val intent = Intent(applicationContext,NotificationActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            putExtra("articlesNotif", gson.toJson(searchNotificationResult))
                        }
//                        Log.i("articles verif", gson.toJson(searchNotificationResult))


                        sendNotificationResult(it, applicationContext, intent)
                        Toast.makeText(
                            applicationContext,
                            "Articles trouvé : " + it.response.docs.size,
                            Toast.LENGTH_LONG
                        )

                        Log.e("Reponse", it.response.docs.size.toString())
                    }
                }
            }
        })


            return Result.success()
        }

    private fun sendNotificationResult(searchNotificationResult: SearchResponse, context: Context, intent: Intent) {

        if (searchNotificationResult.response.docs.isNotEmpty()) {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                applicationContext,
                0, intent, 0
            )
            val title: String =
                searchNotificationResult.response.docs.first().headline.main.toString()
            val numbreOfResponse = searchNotificationResult.response.docs.size.toString()

//        val date = searchNotificationResult.first().date

            val builder = NotificationCompat.Builder(context, App.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_news)
                .setContentTitle(title)
                .setContentText("Result of Search Today is : " + numbreOfResponse)
                .setContentIntent(pendingIntent)
//            .setContentText(date)
                .setAutoCancel(true)


            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }
        Log.e("notif", "notification")


    }

}
