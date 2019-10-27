package com.riyad.p5.data.model.search

fun mapSearchResponseDataToSearchResult (searchResponse: SearchResponse) : SearchResult {

val responseFirst = searchResponse.response.docs.first()
return SearchResult(
    date = responseFirst.pubDate,
    title = responseFirst.headline.printHeadline,
    description = responseFirst.leadParagraph,
    section = responseFirst.newsDesk,
    urlImage = responseFirst.multimedia.first().url

)
}