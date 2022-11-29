package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
interface Reaction {
    fun isApplicable(update: Update): Boolean
    fun getText(update: Update): String

    fun isCommand(update: Update) : Boolean {
        return BOT_NAMES.any {
            update.message.text.contains(it, ignoreCase = true)
        }
    }

    companion object {
        val BOT_NAMES: List<String> = listOf(
            "арнольд",
            "arnold"
        )
    }
}
