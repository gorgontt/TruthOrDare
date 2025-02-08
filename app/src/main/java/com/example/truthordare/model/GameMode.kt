package com.example.truthordare.model

enum class GameMode(val questionList: List<String>) {
    SOFT(listOf("Вопрос 1 SOFT", "Вопрос 2 SOFT", "Вопрос 3 SOFT")),
    HOT(listOf("Вопрос 1 HOT", "Вопрос 2 HOT", "Вопрос 3 HOT")),
    HARD(listOf("Вопрос 1 HARD", "Вопрос 2 HARD", "Вопрос 3 HARD")),
    EXTREME(listOf("Вопрос 1 EXTREME", "Вопрос 2 EXTREME", "Вопрос 3 EXTREME"));
}