package ru.aezhko.implementation.command

import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.implementation.ArnoldoBot

interface Command {
    fun commandText(): String

    fun execute(update: Update, arnoldoBot: ArnoldoBot)
}
