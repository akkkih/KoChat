package me.akkih.kochat.config.enums

import me.akkih.kochat.config.ConfigManager

enum class ConfigMessage {

    NO_PERMISSION_MESSAGE,
    INVALID_USAGE,
    NO_CONSOLE,
    PLAYER_NOT_ONLINE,
    RECEIVED_MESSAGE,
    SENT_MESSAGE,
    DID_NOT_MESSAGE_ANYONE,
    CHAT_WAS_DISABLED,
    CHAT_WAS_ENABLED,
    CHAT_IS_DISABLED,
    JOIN_MESSAGE,
    QUIT_MESSAGE,
    SUCCESSFUL_RELOAD,
    LANGUAGE_NOT_FOUND,
    LANGUAGE_CHANGED_SUCCESSFULLY,
    ADDED_WORD,
    REMOVED_WORD,
    WORD_ALREADY_ADDED,
    WORD_NOT_FOUND,
    NO_WORDS,
    LIST_OF_WORDS;

    fun get() = ConfigManager.getMessage(this)

}