package ru.aezhko.implementation.command.impl

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import ru.aezhko.implementation.ArnoldoBot
import ru.aezhko.implementation.command.Command
import ru.aezhko.implementation.reaction.Reaction

@Component
class NewYearCommand: Command {
    override fun commandText() = "/newyear"

    override fun execute(update: Update, arnoldoBot: ArnoldoBot) {
        arnoldoBot.sendMessage(
            getText(),
            update
        )
    }

    private fun getText(): String {
        val place = PLACES.random()
        return "Ты встретишь новый год $place"
    }

    companion object {
        val PLACES = listOf(
            "в Мариуполе",
            "в Тайланде",
            "на фронте",
            "в тюрьме",
            "в Финляндии",
            "в Бане",
            "на Филлипинах",
            "в окопе",
            "в метро",
            "в пробке",
            "в баре",
            "в говно",
            "под елкой",
            "на Бали",
            "в Херсоне",
            "в Киеве",
            "в Кремле",
            "на зоне",
            "на хате",
            "на донбассе",
            "в подвале под обстрелом",
            "в бомбоубежище",
            "под шконкой",
            "на зоне",
            "в Кроматорске",
            "в Славянске",
            "на передке",
            "на передовой",
            "с автоматом в руках",
            "с винтовкой руках",
            "на наблюдательном пункте",
            "охраняя Мариуполь",
            "в автозаке",
            "на пляже"
        )
    }
}
