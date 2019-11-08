package com.riyad.p5.controller

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.riyad.p5.R

class WebViewActivity : AppCompatActivity() {


    lateinit var webView: WebView
    val webViewClient: WebViewClient = object : WebViewClient() {


    }

    companion object {

        val EXTRA_IMAGE_URL = "imageurl"
        val EXTRA_URL = "url"

    }

    lateinit var imageUrl:String
    lateinit var url:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        webView = findViewById(R.id.web_view)
        webView.webViewClient = webViewClient

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(imageUrl)


    }
}
