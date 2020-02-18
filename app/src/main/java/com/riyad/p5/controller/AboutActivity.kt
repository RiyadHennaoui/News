package com.riyad.p5.controller

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mukesh.MarkdownView
import com.riyad.p5.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

//        val toolbar: Toolbar = findViewById(R.id.toolbar_about)
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val markdownView: MarkdownView = findViewById(R.id.markdown_view)

        markdownView.loadMarkdownFromAssets("README.md")


    }
}
