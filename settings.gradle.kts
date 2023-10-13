rootProject.name = "Shoes App"

include(":androidApp")
include(":shared")
//include(":desktopApp")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenCentral()
    }
}
