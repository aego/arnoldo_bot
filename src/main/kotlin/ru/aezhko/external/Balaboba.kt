package ru.aezhko.external

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Balaboba {
    fun continueText(query: String?): String? {
        val url = "https://yandex.ru/lab/api/yalm/text3"

        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.1 Safari/605.1.15")
        headers.set("Origin", "https://yandex.ru")
        headers.set("Referer", "https://yandex.ru/")

        val entity: HttpEntity<Request> = HttpEntity(Request(query), headers)
        val response = restTemplate.postForObject(url, entity, Response::class.java)

        return response?.text
    }

    private class Request(var query: String?) {
        init {
            val intro = 0
            val filter = 1
        }
    }

    private class Response {
        val text: String? = null
    }
}
