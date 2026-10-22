package dev.nenicadi.cbs

import dev.nenicadi.cbs.config.Config
import net.fabricmc.api.ClientModInitializer
import net.minecraft.resources.Identifier

object CBS: ClientModInitializer {
    private const val MOD_ID: String = "cbs"

    fun id(path: String): Identifier =
        Identifier.fromNamespaceAndPath(MOD_ID, path)

    override fun onInitializeClient() {
        Config.handler.load()
    }
}