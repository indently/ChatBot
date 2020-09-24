package com.codepalace.chatbot

object BotResponse {

    fun responses(message: String): String {
        val random = (0..2).random()

        return if (message.contains("hello".toLowerCase())) {
            when (random) {
                0 -> "Hello there!"
                1 -> "Sup"
                2 -> "Buongiorno!"
                else -> "error"
            }

        } else if (message.contains("how are you".toLowerCase())) {
            when (random) {
                0 -> "I'm doing fine, thanks!"
                1 -> "I'm hungry..."
                2 -> "Pretty good! How about you?"
                else -> "error"
            }

        } else {
            when (random) {
                0 -> "I don't understand..."
                1 -> "Try asking me something different"
                2 -> "Idk"
                else -> "error"
            }
        }
    }
}