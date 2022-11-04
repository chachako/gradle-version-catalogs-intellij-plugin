plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.9.0"
}

group = "com.faendir"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2.1")
    type.set("IC") // Target IDE Platform
    updateSinceUntilBuild.set(false)

    plugins.set(
        listOf(
            "org.toml.lang:222.3739.16",
            "com.intellij.gradle:222.3739.16",
            "org.jetbrains.idea.reposearch",
            "org.jetbrains.idea.maven",
            "org.jetbrains.plugins.gradle.maven",
            "org.jetbrains.kotlin"
        )
    )
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    publishPlugin {
        token.set(project.findProperty("intellijToken") as? String ?: System.getenv("INTELLIJ_TOKEN"))
    }

    runIde {
        jvmArgs("-Xmx8G")
    }
}
