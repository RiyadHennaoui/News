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
import com.riyad.p5.data.model.search.SearchResponse
import com.riyad.p5.data.model.search.mapSearchResponseDataToSearchResult
import com.riyad.p5.utils.RetrofitConstant.Companion.API_KEY
import com.riyad.p5.utils.RetrofitConstant.Companion.BASE_URL
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val NOTIFICATION_ID = 0


class SyncNotificationWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {

    private val noArticleFound = "No Article found today"
    private val dateFormatter = org.threeten.bp.format.DateTimeFormatter.BASIC_ISO_DATE
    private val date = LocalDate.now()
    private val notificationDate = date.format(dateFormatter).toString()
    private val retrofit = retrofitForWorker()
    private val serviceNotificationSearch = retrofit.create(NewYorkTimesAPI::class.java)
    private val notificationDao = App.database.notificationDao().getNotificationUserInput()
    val serverError = "Server error please retry"

    override fun doWork(): Result {

        var paramFilter = "news_desk:("

        notificationDao!!.sections.forEach {
            paramFilter += "\"$it\" "
        }
        paramFilter += ")"
        val callNotificationSearch = serviceNotificationSearch
            .getSearchResponse(
                notificationDao.inputSearchUser,
                paramFilter, notificationDate,
                notificationDate, API_KEY

            )

        callNotificationSearch.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(
                call: Call<SearchResponse>,
                t: Throwable
            ) {


                Toast.makeText(applicationContext,serverError, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {


                response.body()?.let {

                    val gson = Gson()

                    val searchNotificationResult = mapSearchResponseDataToSearchResult(it)


                    if (it.response.docs.isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            noArticleFound,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val intent =
                            Intent(applicationContext, NotificationActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                putExtra("articlesNotif", gson.toJson(searchNotificationResult))
                            }


                        sendNotificationResult(it, applicationContext, intent)
                        Toast.makeText(
                            applicationContext,
                            "Articles found : " + it.response.docs.size,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        return Result.success()
    }

    private fun retrofitForWorker(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun sendNotificationResult(
        searchNotificationResult: SearchResponse,
        context: Context,
        intent: Intent
    ) {

        if (searchNotificationResult.response.docs.isNotEmpty()) {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                applicationContext,
                0, intent, PendingIntent.FLAG_ONE_SHOT

            )
            val title: String =
                searchNotificationResult.response.docs.first().headline.main.toString()
            val numbreOfResponse = searchNotificationResult.response.docs.size.toString()


            val builder = NotificationCompat.Builder(context, App.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_news)
                .setContentTitle(title)
                .setContentText("Result of Search Today is : " + numbreOfResponse)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }

    }

}
