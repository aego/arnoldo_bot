package ru.aezhko.command

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.ArnoldoBot

@Component
class CommandProcessor(commands: List<Command>) {
    private val logger = LoggerFactory.getLogger(CommandProcessor::class.java)
    private val commandsMap: HashMap<String, Command> = HashMap()

    init {
        commands.forEach {
            commandsMap[it.commandText()] = it
        }
    }

    fun process(update: Update, arnoldoBot: ArnoldoBot) {
        val command = update.message.text.replace("@arnoldo_hulordobot", "")
        logger.info("Command $command received")
        commandsMap[command]?.execute(update, arnoldoBot)
    }
}
