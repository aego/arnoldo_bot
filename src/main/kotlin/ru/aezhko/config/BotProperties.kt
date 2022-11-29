package ru.aezhko.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:bot.properties")
@ConfigurationProperties(prefix = "bot")
class BotProperties {
    lateinit var token: String
    lateinit var name: String
}
