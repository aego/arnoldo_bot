package ru.aezhko.reaction.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.reaction.Reaction
import java.util.Random

@Component
class HueReaction(
    val hueMapping: Map<String, List<String>> = mapOf(
        "dima_akhmetov" to listOf(
            "Дима - Хуима",
            "Опять бухал?",
            "Заходит Дима в бар...",
            "Сколько шотов тебе нужно для счастья?",
            "Ну Диман, бля, ну хорош!"
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
            "Сколько либералов нужно чтобы свергнуть Путина?",
            "Нет на свете лучше места, чем любимый автозак?",
            "Рома, сыграй мне на гитаре"
        ),
        "KuZbMa" to listOf(
            "Кузьмич - Хуич",
            "Го в дотку"
        ),
        "dent0n7887" to listOf(
            "Серега - Хуега",
            "Пососи хуйцов, как говорится",
            "А вы тоже слушали трек \"Да пошел ты нахуй, ебаный Серега\"?",
            "Отсоси потом проси",
            "Ебааа, надо ж было такую хуйню сморозить!",
            "Ничего тупее в жизни не читал",
            "Ты сам хоть понял, что сказал?"
        ),
        "kitova1337" to listOf(
            "Китова - Хуетова",
            "Саша - Хуяша",
            "Сааань, откроой",
            "Сань, когда стрим?",
            "Сколько нужно школьников, чтобы соблазнить Китову?"
        ),
    )
): Reaction {
    override fun isApplicable(update: Update): Boolean {
        return false && hueMapping.containsKey(update.message.from.userName)
            && Random().nextInt(0, 15) == 1
    }

    override fun getText(update: Update): String {
        return hueMapping[update.message.from.userName]?.random() ?: "Хуйня из-под ногтя"
    }
}
