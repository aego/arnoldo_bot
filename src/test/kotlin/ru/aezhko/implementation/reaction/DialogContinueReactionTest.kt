package ru.aezhko.implementation.reaction

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DialogContinueReactionTest {
    @Test
    fun queue() {
        val doc: Document = Jsoup.connect("http://www.bashorg.org/casual").get()
        val qDiv = doc.getElementsByClass("q").get(0).getElementsByTag("div")
            .get(2).getElementsByTag("div").get(0)
        val res = qDiv.text().replace("<br />", "\n")

        println(res)
    }
}
