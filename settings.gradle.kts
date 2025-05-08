pluginManagement {
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
    repositories {
        google()
        mavenCentral()
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "Frequent_2"
include(":app")
include(":core")
include(":core:auth")
include(":core:cache")
include(":core:common")
include(":core:error")
include(":core:navigation")
include(":core:network")
include(":core:security")
include(":core:uicommon")
include(":di")
include(":features")
include(":features:auth")
include(":features:profile")
include(":features:login")
include(":features:home")
include(":features:splash")