package me.akkih.kochat

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import me.akkih.kochat.commands.BaseCommand
import me.akkih.kochat.commands.MessageCommand
import me.akkih.kochat.commands.ReplyCommand
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.listeners.MessageListener
import me.akkih.kochat.listeners.PlayerListener
import me.akkih.kochat.utils.ChatUtil
import me.akkih.kochat.utils.JsonUtil
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

lateinit var recentMessages: HashMap<UUID, UUID>
lateinit var main: KoChat

fun CommandSender.sendConfigMessage(message: ConfigMessage) = sendMessage(ChatUtil.format(message.get()))
fun CommandSender.sendConfigMessage(configMessage: ConfigMessage, vararg placeholders: Pair<String, String>) {
    var message = configMessage.get()
    for ((key, value) in placeholders) message = message.replace(key, value)
    sendMessage(ChatUtil.format(message))
}

class KoChat : JavaPlugin() {

    override fun onEnable() {
        logger.info("Starting KoChat...")

        main = this
        recentMessages = HashMap()

        createResources()
        checkIsPluginUpdated()
        registerListeners()
        registerCommands()

        logger.info("KoChat started successfully!")
    }

    private fun createResources() {
        if (!dataFolder.exists()) dataFolder.mkdir()
        saveDefaultConfig()

        saveResource("messages${File.separator}de_de.yml", false)
        saveResource("messages${File.separator}en_us.yml", false)
        saveResource("messages${File.separator}es_es.yml", false)
        saveResource("messages${File.separator}fr_fr.yml", false)
        saveResource("messages${File.separator}pt_br.yml", false)
        saveResource("messages${File.separator}ru_ru.yml", false)

        logger.info("Languages saved.")
    }

    private fun checkIsPluginUpdated() {
        Bukkit.getScheduler().runTaskAsynchronously(this, Runnable {
            val mapper = ObjectMapper()

            val root: JsonNode = try {
                mapper.readValue(JsonUtil.getDataJSON(), JsonNode::class.java)
            } catch (error: JsonProcessingException) {
                throw RuntimeException(error)
            }

            if (root.get("latestVersion").toString() != description.version) {
                logger.severe("You are running an outdated version of KoChat. Please update to the latest version.")
            }
        })
    }

    private fun registerListeners() {
        logger.info("Registering listeners...")
        val manager = Bukkit.getPluginManager()

        manager.registerEvents(MessageListener(), this)
        manager.registerEvents(PlayerListener(), this)
        logger.info("Listeners registered successfully!")
    }

    private fun registerCommands() {
        logger.info("Registering commands...")
        BaseCommand()
        MessageCommand()
        ReplyCommand()
        logger.info("Commands registered successfully!")
    }

    // Other methods
    fun getRecentMessages(): HashMap<UUID, UUID> {
        return recentMessages
    }

}