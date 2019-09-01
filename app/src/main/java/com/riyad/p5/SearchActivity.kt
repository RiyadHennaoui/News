package com.riyad.p5

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import org.threeten.bp.LocalDate

class SearchActivity : AppCompatActivity() {

    private val searchManager = SearchManager()

    private var beginDate: LocalDate? = null
    private var endDate: LocalDate? = null



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

            val sections: List<String> = when {
                checkboxBusiness.isChecked -> listOf("business")
                checkBoxSports.isChecked -> listOf("sports")
                checkBoxThechnology.isChecked -> listOf("thechnology")
                checkBoxFood.isChecked -> listOf("food")
                else -> emptyList()
            }

            when (searchManager.checkUserInput(
                inputUserSearch.query.toString(),
                sections,
                beginDate,
                endDate
            )) {


                SearchManager.UserInputState.VALID -> Toast.makeText(
                    this,
                    "Bravo !! ",
                    Toast.LENGTH_SHORT
                ).show()
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
                        beginDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
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
                        endDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
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

