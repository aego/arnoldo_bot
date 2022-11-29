package ru.aezhko.implementation

import org.telegram.telegrambots.meta.generics.BotOptions
import org.telegram.telegrambots.meta.generics.BotSession
import org.telegram.telegrambots.meta.generics.LongPollingBot

class ArnoldoSession: BotSession {
    private var token: String? = null

    override fun setOptions(options: BotOptions?) {
    }

    override fun setToken(token: String?) {
        this.token = token
    }

    override fun setCallback(callback: LongPollingBot?) {
    }

    override fun start() {
    }

    override fun stop() {
    }

    override fun isRunning(): Boolean {
        return false
    }
}
