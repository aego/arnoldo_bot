package ru.aezhko.command

import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.ArnoldoBot

interface Command {
    fun commandText(): String

    fun execute(update: Update, arnoldoBot: ArnoldoBot)
}
