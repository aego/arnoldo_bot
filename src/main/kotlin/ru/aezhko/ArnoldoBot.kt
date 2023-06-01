package ru.aezhko

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import ru.aezhko.config.BotProperties
import ru.aezhko.external.BashOrg
import ru.aezhko.command.CommandProcessor
import ru.aezhko.reaction.Reaction

@Component
class ArnoldoBot(
    private val properties: BotProperties,
    private val reactions: List<Reaction>,
    private val bashOrg: BashOrg,
    private val commandProcessor: CommandProcessor
): TelegramLongPollingBot() {
    private val logger = LoggerFactory.getLogger(ArnoldoBot::class.java)

    override fun getBotToken(): String {
        return properties.token
    }

    override fun getBotUsername(): String {
        return properties.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage() && update.message.hasText()) {
            val username = update.message.from.userName
            logger.info("$username message received")

            if (isCommand(update)) {
                commandProcessor.process(update, this)
            } else {
                logger.info("Looking for available reactions")
                for (reaction in reactions) {
                    if (reaction.isApplicable(update)) {
                        logger.info("$reaction found applicable")
                        sendMessage(reaction.getText(update), update)
                    }
                }
            }
        }
    }

    fun sendMessage(text: String, update: Update) {
        if (text.length < 5) {
            return
        }

        val message = SendMessage()
        message.chatId = update.message.chatId.toString()
        message.replyToMessageId = update.message.messageId
        message.text = text

        try {
            execute(message)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

//     @Scheduled(cron = "0 0 11 * * ?")
    private fun sendRandomQuote() {
        val quote = bashOrg.getRandomQuote()

        val message = SendMessage()
        message.chatId = TRIDTSATOCHKA_CHAT_ID
        message.text = "Вот вам рандомная цитата с баша: \n\n$quote"

        try {
            execute(message)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun isCommand(update: Update?): Boolean {
        return update?.message?.text?.startsWith("/") ?: false
    }

    companion object {
        private const val TRIDTSATOCHKA_CHAT_ID = "-1001610715896"
    }
}
