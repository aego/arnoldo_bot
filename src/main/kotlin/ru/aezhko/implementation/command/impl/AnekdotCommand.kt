package ru.aezhko.implementation.command.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.data.Anekdots
import ru.aezhko.implementation.ArnoldoBot
import ru.aezhko.implementation.command.Command

@Component
class AnekdotCommand: Command {
    override fun commandText() = "/anekdot"

    override fun execute(update: Update, arnoldoBot: ArnoldoBot) {
        arnoldoBot.sendMessage(
            Anekdots().list.random(),
            update
        )
    }
}
