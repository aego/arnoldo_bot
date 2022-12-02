package ru.aezhko.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.aezhko.ArnoldoBot

@Configuration
class BotConfiguration(
    private val arnoldoBot: ArnoldoBot
) {
    private val logger = LoggerFactory.getLogger(BotConfiguration::class.java)

    @Bean
    fun init(): TelegramBotsApi? {
        return try {
            logger.info("Starting bot...")
            val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
            botsApi.registerBot(arnoldoBot)
            logger.info("Bot started")
            botsApi
        } catch (e: TelegramApiException) {
            e.printStackTrace()
            null
        }
    }
}
