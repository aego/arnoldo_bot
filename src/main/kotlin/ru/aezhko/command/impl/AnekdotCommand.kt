package ru.aezhko.command.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.data.Anekdots
import ru.aezhko.ArnoldoBot
import ru.aezhko.command.Command

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
