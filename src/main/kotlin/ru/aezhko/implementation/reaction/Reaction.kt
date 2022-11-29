package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
interface Reaction {
    fun isApplicable(update: Update): Boolean
    fun getText(update: Update): String
}
