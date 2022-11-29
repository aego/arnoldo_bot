package ru.aezhko.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.aezhko.external.Balaboba
import ru.aezhko.implementation.ArnoldoBot
import ru.aezhko.implementation.ArnoldoSession
import ru.aezhko.implementation.reaction.Reaction

@Configuration
class BotConfiguration(
    private val properties: BotProperties,
    private val reactions: List<Reaction>
) {
    private val logger = LoggerFactory.getLogger(BotConfiguration::class.java)

    @Bean
    fun init(): TelegramBotsApi? {
        return try {
            logger.info("Starting...")
            logger.info("Found reactions: $reactions")
            val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
            botsApi.registerBot(ArnoldoBot(properties, reactions))
            botsApi
            null
        } catch (e: TelegramApiException) {
            e.printStackTrace()
            null
        }
    }
}
