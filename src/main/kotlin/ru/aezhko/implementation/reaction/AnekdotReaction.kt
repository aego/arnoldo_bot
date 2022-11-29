package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.data.Anekdots

@Component
class AnekdotReaction: Reaction {
    override fun isApplicable(update: Update): Boolean {
        return isCommand(update) && update.message.text.lowercase().contains("анекдот")
    }

    override fun getText(update: Update): String {
        return Anekdots().list.random()
    }
}
