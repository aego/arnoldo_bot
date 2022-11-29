package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class NewYearReaction : Reaction {
    override fun isApplicable(update: Update): Boolean {
        return isCommand(update) && update.message.text.lowercase().contains("новый год")
    }

    override fun getText(update: Update): String {
        val place = PLACES.random()
        return "Ты встретишь новый год $place"
    }

    companion object {
        val PLACES = listOf(
            "в Мариуполе",
            "в Тайланде",
            "на фронте",
            "в тюрьме",
            "в Финляндии",
            "в Бане",
            "на Филлипинах",
            "в окопе",
            "в метро",
            "в пробке",
            "в баре",
            "в говно",
            "под елкой",
            "на Бали",
            "в Херсоне",
            "в Киеве",
            "в Кремле",
            "на зоне",
            "на хате",
            "на донбассе"
        )
    }
}
