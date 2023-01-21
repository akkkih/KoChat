package me.akkih.kochat.commands

import me.akkih.kochat.commands.subcommands.HelpSubcommand
import me.akkih.kochat.commands.subcommands.ReloadSubcommand
import me.akkih.kochat.commands.utils.CommandManager
import me.akkih.kochat.commands.utils.SubcommandEnum
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

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
        } else {
            when (args[0].lowercase()) {
                "help" -> helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
                "reload" -> reloadSubcommand.execute(sender, args, SubcommandEnum.RELOAD)
                else -> helpSubcommand.execute(sender, args, SubcommandEnum.HELP)
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String> {
        if (args.size == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.stream(SubcommandEnum.values()).map { it.command.lowercase() }.toList(), ArrayList())
        }

        return mutableListOf()
    }

}