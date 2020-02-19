package com.riyad.p5.controller

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mukesh.MarkdownView
import com.riyad.p5.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(findViewById(R.id.toolbar_about))
        supportActionBar!!.title = "Help"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val markdownView: MarkdownView = findViewById(R.id.markdown_view)

        markdownView.loadMarkdownFromAssets("README.md")


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
