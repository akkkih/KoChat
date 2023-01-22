package me.akkih.kochat.commands

import me.akkih.kochat.commands.subcommands.HelpSubcommand
import me.akkih.kochat.commands.subcommands.LanguageSubcommand
import me.akkih.kochat.commands.subcommands.ReloadSubcommand
import me.akkih.kochat.commands.utils.CommandManager
import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.config.enums.Language
import org.bukkit.command.CommandSender
import org.bukkit.util.StringUtil
import java.util.Arrays

class BaseCommand : CommandManager(
    "kochat",
    arrayOf("kc"),
    "Primary command to handle KoChat",
    "kochat.primarycommand"
) {

    private val helpSubcommand = HelpSubcommand()
    private val reloadSubcommand = ReloadSubcommand()
    private val languageSubcommand = LanguageSubcommand()

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
        } else {
            when (args[0].lowercase()) {
                "help" -> helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
                "reload" -> reloadSubcommand.execute(sender, args, SubcommandEnum.RELOAD)
                "language" -> languageSubcommand.execute(sender, args, SubcommandEnum.LANGUAGE)
                else -> helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String> {
        if (args.size == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.stream(SubcommandEnum.values()).map { it.command.lowercase() }.toList(), ArrayList())
        }

        if (args.size == 2) {
            return when (args[0].lowercase()) {
                "language" -> StringUtil.copyPartialMatches(args[1], Arrays.stream(Language.values()).map { it.name.lowercase() }.toList(), ArrayList())
                else -> mutableListOf()
            }
        }

        return mutableListOf()
    }

}