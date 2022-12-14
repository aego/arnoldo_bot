package ru.aezhko.reaction.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.external.Balaboba
import ru.aezhko.reaction.Reaction
import java.util.LinkedList
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

@Component
class DialogContinueReaction(
    val balaboba: Balaboba
): Reaction {
    private val logger = LoggerFactory.getLogger(DialogContinueReaction::class.java)
    private val chatsContext: ConcurrentHashMap<Long, LinkedList<String>> = ConcurrentHashMap()

    override fun isApplicable(update: Update): Boolean {
        val chatId = update.message.chatId
        val text = update.message.text

        fillContext(chatId, text)

        if (isReplyToBot(update)) {
            val storedContext = chatsContext[chatId]?.joinToString(". ")
            logger.info("Reply received: $update \nwith context\n $storedContext")
        }

        return update.message.chat.type == PRIVATE_CHAT_TYPE
            || isReplyToBot(update)
            || isArnoldMentioned(update)
            || Random.nextInt(0, 9) == 1
    }

    private fun isReplyToBot(update: Update) = update.message.replyToMessage?.from?.id == ARNOLD_BOT_ID

    override fun getText(update: Update): String {
        val chatId = update.message.chatId
        val storedContext = chatsContext[chatId]?.joinToString(". ")
        val balabobaText = balaboba.continueText(storedContext)
        val continuedText = balabobaText?.split(".", "?", "!")

        val res = ArrayList<String>()
        var cnt = 0
        if (continuedText != null) {
            while (res.size < RESULT_SIZE && cnt < continuedText.size) {
                res.add(continuedText[cnt])
                cnt++
            }
        }

        return res.joinToString(". ")
    }

    private fun fillContext(chatId: Long, text: String) {
        if (text.isEmpty()) {
            return;
        }

        if (!chatsContext.containsKey(chatId)) {
            chatsContext[chatId] = LinkedList()
        }

        chatsContext[chatId]?.add(text)

        if (chatsContext[chatId]?.size!! > CONTEXT_FACTOR) {
            chatsContext[chatId]?.removeFirst()
        }
    }

    companion object {
        private const val RESULT_SIZE = 3
        private const val CONTEXT_FACTOR = 4
        private const val ARNOLD_BOT_ID = 5930116678
        private const val PRIVATE_CHAT_TYPE = "private"
    }
}
