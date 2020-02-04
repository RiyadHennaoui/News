package com.riyad.p5.controller

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.riyad.p5.R

class WebViewActivity : AppCompatActivity() {


    lateinit var webView: WebView
    val webViewClient: WebViewClient = object : WebViewClient() {


    }

    companion object {

        const val EXTRA_ARTICLE_URL = "imageurl"


    }

    lateinit var articleUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val toolbar = findViewById<Toolbar>(R.id.tb_web_view)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        articleUrl = intent.getStringExtra(EXTRA_ARTICLE_URL)
        webView = findViewById(R.id.web_view)
        webView.webViewClient = webViewClient

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(articleUrl)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) // Press Back Icon
        {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
