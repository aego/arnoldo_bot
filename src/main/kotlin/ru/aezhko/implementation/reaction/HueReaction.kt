package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.Random

@Component
class HueReaction(
    val hueMapping: Map<String, List<String>> = mapOf(
        "dima_akhmetov" to listOf("Дима - Хуима"),
        "Shariker" to listOf("Андрей - Хуендрей"),
        "romeirox" to listOf("Рома - Хуема"),
        "KuZbMa" to listOf("Кузьмич - Хуич"),
        "dent0n7887" to listOf("Серега - Хуега"),
        "kitova1337" to listOf("Китова - Хуетова"),
    )
) : Reaction {
    override fun isApplicable(update: Update): Boolean {
        return hueMapping.containsKey(update.message.from.userName)
            && Random().nextInt(0, 10) < 2
    }

    override fun getText(update: Update): String {
        return hueMapping[update.message.from.userName]?.random() ?: "Хуйня из-под ногтя"
    }
}
