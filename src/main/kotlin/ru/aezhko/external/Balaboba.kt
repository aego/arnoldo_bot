package ru.aezhko.external

import org.jvnet.hk2.annotations.Service
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Balaboba {
    fun continueText(query: String?): String? {
        val restTemplate = RestTemplate()
        val url = "https://yandex.ru/lab/api/yalm/text3"

        val response = restTemplate.postForObject(
            url,
            Request(query),
            Response::class.java
        )

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
