package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.external.Balaboba
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

@Component
class DialogContinueReaction(
    val balaboba: Balaboba
):Reaction {
    val map: ConcurrentHashMap<Long, String> = ConcurrentHashMap()

    override fun isApplicable(update: Update): Boolean {
        val chatId = update.message.chatId
        var text = update.message.text

        if (map.containsKey(chatId)) {
            text += ". "
            text += map[chatId]
        }
        map[chatId] = text

        return Random.nextInt(0, 5) == 1
    }

    override fun getText(update: Update): String {
        val chatId = update.message.chatId
        val continueText = balaboba.continueText(map[chatId])
            ?.split(".", ",", "?", "!")

        var res = ""
        var cnt = 0
        if (continueText != null) {
            while (res.length < 100 && cnt < continueText.size) {
                res += continueText[cnt] + ". "
                cnt++
            }
        }

        map.remove(chatId)
        return res
    }
}
