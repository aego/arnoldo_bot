package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.Random

@Component
class HueReaction(
    val hueMapping: Map<String, List<String>> = mapOf(
        "dima_akhmetov" to listOf(
            "Дима - Хуима",
            "Опять бухал?"
        ),
        "Shariker" to listOf(
            "Андрей - Хуендрей",
            "Андрей, опять вместо работы в чатах сидишь?"
        ),
        "romeirox" to listOf(
            "Рома - Хуема",
            "Роман, когда там ближайший митинг?",
            "Блэт, Нэвээльный"
        ),
        "KuZbMa" to listOf(
            "Кузьмич - Хуич"
        ),
        "dent0n7887" to listOf(
            "Серега - Хуега",
            "Сереега, открой!",
            "Пососи хуйцов, как говорится"
        ),
        "kitova1337" to listOf(
            "Китова - Хуетова",
            "Саша - Хуяша"
        ),
    )
) : Reaction {
    override fun isApplicable(update: Update): Boolean {
        return hueMapping.containsKey(update.message.from.userName)
            && Random().nextInt(0, 11) < 3
    }

    override fun getText(update: Update): String {
        return hueMapping[update.message.from.userName]?.random() ?: "Хуйня из-под ногтя"
    }
}
