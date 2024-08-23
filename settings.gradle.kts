rootProject.name = "sodium"

pluginManagement {
    repositories {
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.neoforged.net/releases/") }
        gradlePluginPortal()
    }

    includeBuild("build-logic")
}

include("common")
include("fabric")
include("neoforge")