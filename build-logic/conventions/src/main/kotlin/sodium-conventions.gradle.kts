import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `maven-publish`
}

val libs = the<LibrariesForLibs>()

fun createVersionString(): String {
    val builder = StringBuilder()

    val isReleaseBuild = project.hasProperty("build.release")
    val buildId = System.getenv("GITHUB_RUN_NUMBER")

    if (isReleaseBuild) {
        builder.append(libs.versions.sodium.get())
    } else {
        builder.append(libs.versions.sodium.get().substringBefore('-'))
        builder.append("-snapshot")
    }

    builder.append("+mc").append(libs.versions.minecraft.get())

    if (!isReleaseBuild) {
        if (buildId != null) {
            builder.append("-build.${buildId}")
        } else {
            builder.append("-local")
        }
    }

    return builder.toString()
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}