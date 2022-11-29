package ru.aezhko.implementation

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import ru.aezhko.config.BotProperties
import ru.aezhko.implementation.reaction.Reaction

class ArnoldoBot(
    private val properties: BotProperties,
    private val reactions: List<Reaction>
): TelegramLongPollingBot() {
    private val logger = LoggerFactory.getLogger(ArnoldoBot::class.java)

    override fun getBotToken(): String {
        return properties.token
    }

    override fun getBotUsername(): String {
        return properties.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage() && update!!.message.hasText()) {
            val username = update.message.from.userName
            logger.info("$username message received")

            for (reaction in reactions) {
                if (reaction.isApplicable(update)) {
                    logger.info("$reaction found applicable")
                    sendMessage(reaction.getText(update), update)
                }
            }
        }
    }

    private fun sendMessage(text: String, update: Update) {
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
}
