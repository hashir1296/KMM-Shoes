plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
   // id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
         //   export("dev.icerock.moko:resources:0.23.0")
            //  export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val ktorVersion = "2.3.2"
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class) implementation(compose.components.resources)
                implementation("media.kamel:kamel-image:0.7.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0-RC")
                api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
                api("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatfrom
             //   api("dev.icerock.moko:resources:0.23.0")
         //       api("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.4.3")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                // https://mvnrepository.com/artifact/com.google.accompanist/accompanist-systemuicontroller
                implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")

            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.4.3")
                implementation("io.ktor:ktor-client-java:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")// Use the latest version

            }
        }

    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/LICENSE")
            add("META-INF/*.properties")
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
            add("META-INF/versions/9/previous-compilation-data.bin")
        }
    }
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    // commonTestImplementation("dev.icerock.moko:resources-test:0.23.0")
}
/*multiplatformResources {
    multiplatformResourcesPackage = "com.myapplication.common" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
}*/
