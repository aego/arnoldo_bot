package ru.aezhko.implementation.reaction

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.Random

@Component
class HueReaction(
    val hueMapping: Map<String, List<String>> = mapOf(
        "dima_akhmetov" to listOf(
            "Дима - Хуима",
            "Опять бухал?",
            "Заходит Дима в бар...",
            "Сколько шотов тебе нужно для счастья?"
        ),
        "Shariker" to listOf(
            "Андрей - Хуендрей",
            "Андрей, опять вместо работы в чатах сидишь?",
            "А плащ ты свой дома не забыл?"
        ),
        "romeirox" to listOf(
            "Рома - Хуема",
            "Роман, когда там ближайший митинг?",
            "Блэт, Нэвээльный",
            "Сколько либералов нужно чтобы свергнуть Путина?"
        ),
        "KuZbMa" to listOf(
            "Кузьмич - Хуич"
        ),
        "dent0n7887" to listOf(
            "Серега - Хуега",
            "Пососи хуйцов, как говорится",
            "А вы тоже слушали трек \"Да пошел ты нахуй, ебаный Серега\"?"
        ),
        "kitova1337" to listOf(
            "Китова - Хуетова",
            "Саша - Хуяша",
            "Сааань, откроой"
        ),
    )
): Reaction {
    override fun isApplicable(update: Update): Boolean {
        return hueMapping.containsKey(update.message.from.userName)
            && Random().nextInt(0, 9) == 1
    }

    override fun getText(update: Update): String {
        return hueMapping[update.message.from.userName]?.random() ?: "Хуйня из-под ногтя"
    }
}
