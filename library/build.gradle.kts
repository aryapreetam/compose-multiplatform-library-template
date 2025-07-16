import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "com.example.mylibrary"
version = "0.0.1"

kotlin {
    jvm("desktop")

    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.example.mylibrary"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "Library Name"
        description = "Library Description"
        inceptionYear = "2025"
        url = "https://github.com/username/mylibrary/"
        licenses {
            license {
                name = "Apache License"
                url = "https://opensource.org/license/apache-2-0"
            }
        }
        developers {
            developer {
                id = "Developer ID"
                name = "Developer Name"
                url = "Develper GitHub URL"
                email = "Developer Email"
            }
        }
        scm {
            url = "Library GitHub URL"
            connection = "scm:git:git://github.com/username/mylibrary.git"
            developerConnection = "scm:git:ssh://git@github.com/username/mylibrary.git"
        }
    }
}
