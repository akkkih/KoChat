package me.akkih.kochat.enums

enum class Language {

    DE_DE,
    EN_US,
    ES_ES,
    FR_FR,
    PT_BR,
    RU_RU;

    companion object {
        private val languages = Language.values().map { it.toString() }

        fun exists(language: String): Boolean {
            return languages.contains(language)
        }
    }

}