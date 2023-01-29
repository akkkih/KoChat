package me.akkih.kochat.commands

import me.akkih.kochat.commands.subcommands.*
import me.akkih.kochat.commands.utils.CommandManager
import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.commands.utils.SubcommandEnum.*
import me.akkih.kochat.enums.Language
import me.akkih.kochat.main
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.*

class BaseCommand : CommandManager(
    "kochat",
    arrayOf("kc"),
    "Primary command to handle KoChat",
    "kochat.primarycommand"
) {

    private val helpSubcommand = HelpSubcommand()
    private val reloadSubcommand = ReloadSubcommand()
    private val languageSubcommand = LanguageSubcommand()
    private val wordSubcommand = WordSubcommand()
    private val toggleChatSubcommand = ToggleChatSubcommand()

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            helpSubcommand.execute(sender, args, HELP)
        } else {
            when (args[0].lowercase()) {
                "help" -> helpSubcommand.execute(sender, args, HELP)
                "reload" -> reloadSubcommand.execute(sender, args, RELOAD)
                "language" -> languageSubcommand.execute(sender, args, LANGUAGE)
                "words" -> wordSubcommand.execute(sender, args, WORDS)
                "togglechat" -> toggleChatSubcommand.execute(sender, args, TOGGLECHAT)
                else -> helpSubcommand.execute(sender, args, HELP)
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String> {
        if (args.size == 1) {
            return StringUtil.copyPartialMatches(
                args[0],
                Arrays.stream(SubcommandEnum.values()).map { it.command.lowercase() }.toList(),
                ArrayList()
            )
        }

        if (args.size == 2) {
            return when (args[0].lowercase()) {
                "language" -> StringUtil.copyPartialMatches(
                    args[1],
                    Arrays.stream(Language.values()).map { it.name.lowercase() }.toList(),
                    ArrayList()
                )

                "words" -> StringUtil.copyPartialMatches(args[1], listOf("add", "remove", "list"), ArrayList())
                else -> mutableListOf()
            }
        }

        if (args.size == 3) {
            return when (args[1].lowercase()) {
                "remove" -> StringUtil.copyPartialMatches(args[2], main.config.getStringList("swears"), ArrayList())
                else -> mutableListOf()
            }
        }

        return mutableListOf()
    }

}