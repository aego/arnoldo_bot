package ru.aezhko.external

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component

@Component
class BashOrg {
    fun getRandomQuote(): String {
        val doc: Document = Jsoup.connect("http://www.bashorg.org/casual").get()
        val qDiv = doc.getElementsByClass("q").get(0).getElementsByTag("div")
            .get(2).getElementsByTag("div").get(0)
        val res = qDiv.text().replace("<br />", "\n")

        return res
    }
}
