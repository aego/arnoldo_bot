package ru.aezhko.implementation.reaction

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.external.Balaboba
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.log
import kotlin.random.Random

@Component
class DialogContinueReaction(
    val balaboba: Balaboba
):Reaction {
    private val map: ConcurrentHashMap<Long, String> = ConcurrentHashMap()
    private val logger = LoggerFactory.getLogger(DialogContinueReaction::class.java)

    override fun isApplicable(update: Update): Boolean {
        val chatId = update.message.chatId
        val text = update.message.text

        logger.info("ChatId: $chatId")

        if (map.containsKey(chatId)) {
            val newText = map[chatId] + ". " + text
            map[chatId] = newText
        } else {
            map[chatId] = text
        }

        return Random.nextInt(0, 7) == 1
    }

    override fun getText(update: Update): String {
        val chatId = update.message.chatId
        val storedContext = map[chatId]?.take(150)
        logger.info("Context: $storedContext")

        val balabobaText = balaboba.continueText(storedContext)

        logger.info("BalabobaText: $balabobaText")

        val continuedText = balabobaText
            ?.split(".", ",", "?", "!")

        var res = ""
        var cnt = 0
        if (continuedText != null) {
            while (res.length < 100 && cnt < continuedText.size) {
                res += continuedText[cnt]
                res += ". "
                cnt++
            }
        }

        map.remove(chatId)
        return res
    }
}
