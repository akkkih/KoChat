package me.akkih.kochat.config.enums

import me.akkih.kochat.config.ConfigManager

enum class ConfigMessage {

    NO_PERMISSION_MESSAGE,
    INVALID_USAGE,
    JOIN_MESSAGE,
    QUIT_MESSAGE,
    SUCCESSFUL_RELOAD,
    LANGUAGE_NOT_FOUND,
    LANGUAGE_CHANGED_SUCCESSFULLY,
    ADDED_WORD,
    REMOVED_WORD,
    WORD_ALREADY_ADDED,
    WORD_NOT_FOUND,
    NO_WORDS;

    fun get() = ConfigManager.getMessage(this)
}