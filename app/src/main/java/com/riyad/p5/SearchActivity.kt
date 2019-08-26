package com.riyad.p5

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class SearchActivity : AppCompatActivity() {

    private val searchManager = SearchManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)


        val searchBtn = findViewById<Button>(R.id.btn_search)
        val inputUserSearch = findViewById<SearchView>(R.id.et_search)

        searchBtn.setOnClickListener {
            if (searchManager.checkUserInput(inputUserSearch.query.toString())){

                Toast.makeText(this,"Bien",Toast.LENGTH_SHORT)
                        .show()

            }else {

                Toast.makeText(this,"pas Bien",Toast.LENGTH_SHORT)
                        .show()

            }
        }

    }



}

