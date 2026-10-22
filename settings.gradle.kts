pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "CBS"

stonecutter {
    create(rootProject) {
        versions(
            "26.3", "26.2",
            "26.1.2", "26.1.1", "26.1"
        )
    }
}