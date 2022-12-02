package ru.aezhko.command.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.ArnoldoBot
import ru.aezhko.command.Command
import java.util.Random

@Component
class CockSizeCommand(val random: Random = Random()): Command {
    override fun commandText() = "/cocksize"

    override fun execute(update: Update, arnoldoBot: ArnoldoBot) {
        arnoldoBot.sendMessage(
            getText(),
            update
        )
    }

    private fun getText(): String {
        val size = when(random.nextInt(0, 3)) {
            0 -> random.nextInt(1, 7)
            1 -> random.nextInt(8, 13)
            2 -> random.nextInt(13, 28)
            else -> 1
        }

        return "Размер твоего члена - $size см!"
    }
}
