plugins {
    kotlin("jvm") version "2.4.20"
    id("net.fabricmc.fabric-loom") version "1.18-SNAPSHOT"
}

repositories {
    maven("https://maven.isxander.dev/releases")
    maven("https://maven.terraformersmc.com")
    mavenCentral()
}

version = "${project.property("mod.version")}+${sc.current.version}"
base.archivesName = rootProject.name

val jdkVersion: Int = 25

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")

    implementation("net.fabricmc:fabric-loader:${project.property("fabric.loader")}")
    implementation("net.fabricmc:fabric-language-kotlin:${project.property("fabric.kotlin_loader_version")}")
    implementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric.version")}")

    implementation("dev.isxander:yet-another-config-lib:${project.property("yacl.version")}")
    implementation("com.terraformersmc:modmenu:${project.property("modmenu.version")}")
}

tasks.processResources {
    val props = mapOf(
        "version" to version,
        "minecraft_version" to sc.current.version,
        "loader_version" to project.property("fabric.loader"),
        "kotlin_loader_version" to project.property("fabric.kotlin_loader_version"),
        "yacl_version" to project.property("yacl.version")
    )

    inputs.properties(props)

    listOf("fabric.mod.json").forEach {
        filesMatching(it) { expand(props) }
    }
}

kotlin { jvmToolchain(jdkVersion) }
java { toolchain.languageVersion = JavaLanguageVersion.of(jdkVersion) }