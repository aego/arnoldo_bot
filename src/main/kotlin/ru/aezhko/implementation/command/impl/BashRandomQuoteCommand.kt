package ru.aezhko.implementation.command.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.external.BashOrg
import ru.aezhko.implementation.ArnoldoBot
import ru.aezhko.implementation.command.Command

@Component
class BashRandomQuoteCommand(val bashOrg: BashOrg): Command {
    override fun commandText() = "/bash_quote"

    override fun execute(update: Update, arnoldoBot: ArnoldoBot) {
        arnoldoBot.sendMessage(bashOrg.getRandomQuote(), update)
    }
}
