pluginManagement {
    plugins {
        id("com.android.application") version "8.5.2"   // или 8.7.2
        id("org.jetbrains.kotlin.android") version "2.0.21"
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
        id("com.google.dagger.hilt.android") version "2.48"
    }
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
//plugins {
//    id("org.jetbrains.kotlin.android") apply false
//}
rootProject.name = "Android_dolg1"
include(":app")