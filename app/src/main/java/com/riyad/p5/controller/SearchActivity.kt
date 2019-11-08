package com.riyad.p5.controller

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.riyad.p5.R
import com.riyad.p5.data.model.search.SearchResponse
import com.riyad.p5.data.model.search.mapSearchResponseDataToSearchResult
import com.riyad.p5.data.model.ui.Article
import kotlinx.android.synthetic.main.article_layout.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    private val searchManager = SearchManager()

    private var inputBeginDate: LocalDate? = null
    private var inputEndDate: LocalDate? = null
    private lateinit var adapterSearch: MainAdapter
    private lateinit var mData: List<Article>
    lateinit var rvSearch: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)


        val searchBtn = findViewById<Button>(R.id.btn_search)


        val inputUserSearch = findViewById<SearchView>(R.id.et_search)
        val checkboxBusiness = findViewById<CheckBox>(R.id.checkbox_1)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkbox_2)
        val checkBoxThechnology = findViewById<CheckBox>(R.id.checkbox_3)
        val checkBoxFood = findViewById<CheckBox>(R.id.checkbox_4)
        val beginDateTextView = findViewById<TextView>(R.id.begin_date)
        val endDateTextView = findViewById<TextView>(R.id.end_date)
        rvSearch = findViewById(R.id.rv_search_article)

        searchBtn.setOnClickListener {


            val sections: ArrayList<String> = ArrayList()


            if (checkboxBusiness.isChecked) sections.add("business")
            if (checkBoxSports.isChecked) sections.add("sports")
            if (checkBoxThechnology.isChecked) sections.add("technology")
            if (checkBoxFood.isChecked) sections.add("food")



            Log.i("SearchActivity", Arrays.toString(sections.toArray()))

            when (searchManager.checkUserInput(
                inputUserSearch.query.toString(),
                sections,
                inputBeginDate,
                inputEndDate
            )) {


                SearchManager.UserInputState.VALID -> {

                    //TODO faire une variable local pour les sections a envoyer ( news_desk:("..." "...") )

                    var paramFilter = "news_desk:("

                    sections.forEach {
                        paramFilter += "\"$it\" "
                    }
                    paramFilter += ")"

                    Log.i("SearchActivity", paramFilter)


                    //TODO faire deux varibles avec conversion de date (YYYYMMDD)

                    val dateFormatter = DateTimeFormatter.BASIC_ISO_DATE

                    val beginDate = inputBeginDate?.format(dateFormatter).toString()
                    val endDate = inputEndDate?.format(dateFormatter).toString()


                    //TODO Retrofit appel


                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://api.nytimes.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val serviceSearch = retrofit.create(NewYorkTimesAPI::class.java)

                    val callSearch = serviceSearch
                        .getSearchResponse(
                            inputUserSearch.query.toString(),
                            paramFilter,
                            beginDate,
                            endDate,
                            "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5"
                        )
                    callSearch.enqueue(object : Callback<SearchResponse> {

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
                            response?.body()?.let {
                                val gson = Gson()
                                Log.i(
                                    "Response",
                                    "Yeahhh !! : ${gson.toJson(it)}  "
                                )
                                val searchResponseResult = mapSearchResponseDataToSearchResult(it)

                                searchBtn.onEditorAction(EditorInfo.IME_ACTION_DONE)
                                updateRv(searchResponseResult)
                                intiRecyclerView()


                                //  val intent = Intent(this@SearchActivity,NotificationActivity::class.java)
                                // intent.putExtra("articles", gson.toJson(searchResponseResult))
                                //  startActivity(intent)

                                //TODO créer un adaptater et renseigner les valeurs de searchResponseResult.

                                //TODO donner cet adaptater au recyclerview


                                Log.i(
                                    "Response",
                                    "Yeahhh !! : ${gson.toJson(searchResponseResult)}  >>  " + response.message() + response.isSuccessful
                                )

                            }


                        }

                    })


                    //TODO Si le résultat est bon faire le mapping


                    //TODO Afficher la liste mapper au recyclerView


                }
                SearchManager.UserInputState.NO_USER_INPUT -> Toast.makeText(
                    this,
                    "Merci de remplir le champs ",
                    Toast.LENGTH_SHORT
                ).show()
                SearchManager.UserInputState.NO_SECTION_SELECTED -> Toast.makeText(
                    this,
                    "please selcet Section ",
                    Toast.LENGTH_SHORT
                ).show()
                SearchManager.UserInputState.INCOHERENT_DATES -> Toast.makeText(
                    this,
                    "begin date shouldn't be after end date ",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }



        LocalDate.now().let { now: LocalDate ->

            beginDateTextView.setOnClickListener {
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                        @SuppressLint("SetTextI18n")
                        beginDateTextView.text = "$dayOfMonth / ${monthOfYear + 1}  /  $year"
                        inputBeginDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)

                    },
                    now.year,
                    now.monthValue - 1,
                    now.dayOfMonth
                )

                dpd.show()
            }

            endDateTextView.setOnClickListener {
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                        @SuppressLint("SetTextI18n")
                        endDateTextView.text = "$dayOfMonth / ${monthOfYear + 1}  /  $year"
                        inputEndDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                    },
                    now.year,
                    now.monthValue - 1,
                    now.dayOfMonth
                )

                dpd.show()
            }

        }


    }

    fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val myView: View = inflater!!.inflate(R.layout.article_layout, container, false)


        return myView
    }

    private fun updateRv(searchResponseResult: List<Article>) {
        mData = searchResponseResult
        adapterSearch = MainAdapter(this)
        adapterSearch.setData(searchResponseResult)
    }


    private fun intiRecyclerView() {

        rvSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = adapterSearch
        }


    }




}

