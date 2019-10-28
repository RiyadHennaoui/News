package com.riyad.p5.data.model.search

import com.riyad.p5.data.model.ui.Article

fun mapSearchResponseDataToSearchResult(searchResponse: SearchResponse): List<Article> {
    val result: ArrayList<Article> = ArrayList()
    searchResponse.response.docs.forEach { doc ->

        val article = Article(
            doc.headline.printHeadline,
            doc.pubDate,
            doc.newsDesk,
            doc.multimedia.first().url,
            doc.leadParagraph
        )
        result.add(article)
    }
    return result
}