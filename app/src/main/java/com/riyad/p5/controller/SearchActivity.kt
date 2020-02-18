package com.riyad.p5.controller

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.riyad.p5.R
import com.riyad.p5.data.model.search.SearchResponse
import com.riyad.p5.data.model.search.mapSearchResponseDataToSearchResult
import com.riyad.p5.data.model.ui.Article
import com.riyad.p5.utils.RetrofitConstant
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private val searchManager = SearchManager()

    private var inputBeginDate: LocalDate? = null
    private var inputEndDate: LocalDate? = null
    private lateinit var adapterSearch: MainAdapter
    private lateinit var mData: List<Article>
    private lateinit var rvSearch: RecyclerView


    // Toast Messages

    private val noUserInputString ="Merci de remplir le champs"
    private val noSectionSelected = "please select Section "
    private val incoherentDate = "begin date shouldn't be after end date "


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


        // ACTION
        searchBtn.setOnClickListener {

            searchBtn.onEditorAction(EditorInfo.IME_ACTION_DONE)

            val sections: ArrayList<String> = ArrayList()

            if (checkboxBusiness.isChecked) sections.add("business")
            if (checkBoxSports.isChecked) sections.add("sports")
            if (checkBoxThechnology.isChecked) sections.add("technology")
            if (checkBoxFood.isChecked) sections.add("food")


            when (searchManager.checkUserInput(
                inputUserSearch.query.toString(),
                sections,
                inputBeginDate,
                inputEndDate
            )) {


                SearchManager.UserInputState.VALID -> {

                    var paramFilter = "news_desk:("

                    sections.forEach {
                        paramFilter += "\"$it\" "
                    }
                    paramFilter += ")"

                    val dateFormatter = DateTimeFormatter.BASIC_ISO_DATE
                    val beginDate = inputBeginDate?.format(dateFormatter).toString()
                    val endDate = inputEndDate?.format(dateFormatter).toString()

                    val retrofit = retrofitCall()

                    val serviceSearch = retrofit.create(NewYorkTimesAPI::class.java)

                    val callSearch = serviceSearch
                        .getSearchResponse(
                            inputUserSearch.query.toString(),
                            paramFilter,
                            beginDate,
                            endDate,
                            RetrofitConstant.API_KEY
                        )

                    // HTTP Resquest
                    searchCall(callSearch)


                }
                // Toast for error case
                SearchManager.UserInputState.NO_USER_INPUT -> Toast.makeText(
                    this,
                    noUserInputString,
                    Toast.LENGTH_SHORT
                ).show()
                SearchManager.UserInputState.NO_SECTION_SELECTED -> Toast.makeText(
                    this,
                    noSectionSelected,
                    Toast.LENGTH_SHORT
                ).show()
                SearchManager.UserInputState.INCOHERENT_DATES -> Toast.makeText(
                    this,
                    incoherentDate,
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

        // Date Picker
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

    private fun searchCall(callSearch: Call<SearchResponse>) {
        callSearch.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(
                call: Call<SearchResponse>,
                t: Throwable
            ) {


            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                response.body()?.let {
                    val searchResponseResult = mapSearchResponseDataToSearchResult(it)
                    updateRv(searchResponseResult)
                    intiRecyclerView()
                }
            }
        })
    }

    private fun retrofitCall(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
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

