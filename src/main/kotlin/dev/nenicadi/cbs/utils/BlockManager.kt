package dev.nenicadi.cbs.utils

import dev.nenicadi.cbs.config.Config
import net.minecraft.client.Minecraft
import net.minecraft.world.level.block.state.BlockState

object BlockManager {
    val configInstance: Config = Config.handler.instance()
    val instance = Minecraft.getInstance()

    private fun getNamespaceFromState(state: BlockState): String =
        instance.modelManager.blockStateModelSet[state].particleMaterial()
            .sprite.contents().name().namespace

    fun isCancelled(state: BlockState): Boolean {
        val namespace = getNamespaceFromState(state)
        return if (configInstance.detectSel == Config.DetectSel.BLACKLIST)
            !configInstance.blacklist.contains(namespace) else
            configInstance.whitelist.contains(namespace)
    }
}