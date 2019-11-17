package com.riyad.p5.data.model.search

import android.util.Log
import com.riyad.p5.data.model.ui.Article

fun mapSearchResponseDataToSearchResult(searchResponse: SearchResponse): List<Article> {
    val result: ArrayList<Article> = ArrayList()
    searchResponse.response.docs.forEach { doc ->

       Log.e("imageUrl", getImageUrl(doc))

        val article = Article(
            doc.headline.printHeadline,
            doc.pubDate,
            doc.newsDesk,
            getImageUrl(doc),
            doc.leadParagraph
        )
        result.add(article)
    }
    return result
}

fun getImageUrl(doc: Doc): String{

    val minPixelSize = 150

    doc.multimedia.forEach { multimedia ->

        if (multimedia.height >= minPixelSize && multimedia.width >= minPixelSize){

            return "https://static01.nyt.com/" + multimedia.url

        }

    }

    return ""

}