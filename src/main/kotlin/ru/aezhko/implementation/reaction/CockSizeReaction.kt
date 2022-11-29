package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.Random

@Component
class CockSizeReaction(
    val random: Random = Random()
) : Reaction {
    override fun isApplicable(update: Update): Boolean {
        return isCommand(update) && update.message.text.lowercase().contains("член")
    }

    override fun getText(update: Update): String {
        val size = when(random.nextInt(0, 2)) {
            0 -> random.nextInt(1, 7)
            1 -> random.nextInt(8, 13)
            2 -> random.nextInt(13, 28)
            else -> 1
        }

        return "Размер твоего члена - $size см!"
    }
}
