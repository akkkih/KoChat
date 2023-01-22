package me.akkih.kochat.commands.utils

import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.utils.ChatUtil
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand

abstract class CommandManager(
    command: String,
    aliases: Array<String>,
    description: String,
    permission: String
) : BukkitCommand(command) {

    init {
        this.aliases = listOf(*aliases)
        this.description = description
        this.permission = permission
        this.permissionMessage(Component.text(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.NO_PERMISSION_MESSAGE))))

        try {
            val field = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            field.isAccessible = true
            val map = field[Bukkit.getServer()] as CommandMap
            map.register(command, this)
        } catch (exception: NoSuchFieldException) {
            exception.printStackTrace()
        } catch (exception: IllegalAccessException) {
            exception.printStackTrace()
        }
    }

    override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
        execute(sender, args)
        return true
    }

    abstract fun execute(sender: CommandSender, args: Array<String>)

    @Throws(IllegalArgumentException::class)
    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> {
        return onTabComplete(sender, args)
    }

    abstract fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String>

}