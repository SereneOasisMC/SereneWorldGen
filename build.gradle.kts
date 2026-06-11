plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenLocal()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        url = uri("https://repo.onarandombox.com/content/groups/public/")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")

    implementation(libs.org.reflections.reflections)
    api(libs.com.github.stefvanschie.inventoryframework)
    api(libs.com.google.guava.guava)

    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly(libs.org.popcraft.chunky.common)
    compileOnly(libs.com.onarandombox.multiversecore.multiverse.core)
    compileOnly(libs.com.github.nivixx.ndatabase.ndatabase.api)
    compileOnly(libs.com.sk89q.worldguard.worldguard.bukkit)
    compileOnly(libs.com.github.milkbowl.vaultapi) {
        exclude(group = "org.bukkit", module = "bukkit")
    }
    compileOnly(libs.com.fastasyncworldedit.fastasyncworldedit.bukkit) {
        exclude(group = "org.lz4", module = "lz4-java")
    }
}

group = "com.sonorous"
version = "1.0-SNAPSHOT"
description = "SonorousWorldGen"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

// Use Maven shade to bundle reflections
tasks {
    jar {
        from(configurations.runtimeClasspath.get()
            .filter { it.name.contains("reflections") || it.name.contains("javassist") }
            .map { zipTree(it) })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    runServer {
        pluginJars(jar.get().archiveFile)
    }
}