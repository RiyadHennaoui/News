package com.riyad.p5.controller

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.riyad.p5.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    private val searchManager = SearchManager()

    private var inputBeginDate: LocalDate? = null
    private var inputEndDate: LocalDate? = null

    private var DateFormat: DateTimeFormatter? = null


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

                    sections.forEach{
                        paramFilter+= "\"$it\" "
                    }
                    paramFilter+=")"

                    Log.i("SearchActivity",paramFilter)


                    //TODO faire deux varibles avec conversion de date (YYYYMMDD)

                    val dateFormatter = DateTimeFormatter.BASIC_ISO_DATE

                    val beginDate = inputBeginDate?.format(dateFormatter).toString()
                    val endDate = inputEndDate?.format(dateFormatter).toString()

                    Log.i("SearchActivity", beginDate)
                    Log.i("SearchActivity", endDate)


                    //TODO Retrofit appel





                    //TODO Si le résultat est bon faire le mapping



                    //TODO Envoyer la liste mapper à l'activité résultat


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
                    now.monthValue,
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
                    now.monthValue,
                    now.dayOfMonth
                )

                dpd.show()
            }

        }


    }


}

