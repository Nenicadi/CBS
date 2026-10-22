package dev.nenicadi.cbs.config

import dev.isxander.yacl3.api.NameableEnum
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.controller.ControllerBuilder
import dev.isxander.yacl3.api.controller.StringControllerBuilder
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.ConfigField
import dev.isxander.yacl3.config.v2.api.SerialEntry
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen
import dev.isxander.yacl3.config.v2.api.autogen.EnumCycler
import dev.isxander.yacl3.config.v2.api.autogen.ListGroup
import dev.isxander.yacl3.config.v2.api.autogen.ListGroup.ControllerFactory
import dev.isxander.yacl3.config.v2.api.autogen.ListGroup.ValueFactory
import dev.isxander.yacl3.config.v2.api.autogen.OptionAccess
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder
import dev.nenicadi.cbs.CBS
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import java.util.*

class Config {
    companion object {
        private const val CATEGORY: String = "basic"

        val handler: ConfigClassHandler<Config> = ConfigClassHandler
            .createBuilder(Config::class.java)
            .id(CBS.id("config"))
            .serializer {
                GsonConfigSerializerBuilder.create(it)
                    .setPath(FabricLoader.getInstance().configDir.resolve("cbs.json5"))
                    .setJson5(true)
                    .build()
            }
            .build()
    }

    @AutoGen(category = CATEGORY)
    @EnumCycler
    @SerialEntry
    @Suppress("UNUSED")
    var detectSel: DetectSel = DetectSel.BLACKLIST

    @AutoGen(category = CATEGORY)
    @ListGroup(valueFactory = ListFactory::class, controllerFactory = ListFactory::class)
    @SerialEntry
    @Suppress("UNUSED")
    var whitelist: List<String> = listOf()

    @AutoGen(category = CATEGORY)
    @ListGroup(valueFactory = ListFactory::class, controllerFactory = ListFactory::class)
    @SerialEntry
    @Suppress("UNUSED")
    var blacklist: List<String> = listOf("minecraft")

    enum class DetectSel : NameableEnum {
        BLACKLIST,
        WHITELIST;

        override fun getDisplayName(): Component =
            Component.translatable("yacl3.config.cbs:config.detectSel." + name.lowercase(Locale.getDefault()))
    }

     class ListFactory : ValueFactory<String>, ControllerFactory<String> {
        override fun provideNewValue(): String = ""
        override fun createController(
            annotation: ListGroup,
            field: ConfigField<List<String>>,
            storage: OptionAccess,
            option: Option<String>
        ): ControllerBuilder<String> =
            StringControllerBuilder.create(option)
     }
}