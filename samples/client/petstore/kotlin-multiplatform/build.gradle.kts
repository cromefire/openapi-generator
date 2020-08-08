plugins {
    kotlin("multiplatform") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
}

group = "org.openapitools"
version = "1.0.0"

val ktorVersion = "1.3.2"
val kotlinxIoVersion = "0.1.16"

repositories {
    jcenter()
}

kotlin {
    /*
     * To find out how to configure the targets, please follow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                // Setup the Kotlin compiler options for the 'main' compilation:
                jvmTarget = "1.8"
            }
        }
    }
    ios {
        binaries {
            framework {
                freeCompilerArgs = listOf("-Xobjc-generics")
            }
        }
    }
    js()

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/common/main")

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-io:$kotlinxIoVersion")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-json:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }

        val commonTest by getting {
            kotlin.srcDir("src/common/test")

            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
            }
        }

        val jvmMain by getting {
            kotlin.srcDir("src/jvm/main")

            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-io-jvm:$kotlinxIoVersion")
                api("io.ktor:ktor-client-core-jvm:$ktorVersion")
                api("io.ktor:ktor-client-json-jvm:$ktorVersion")
                api("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
            }
        }

        val jvmTest by getting {
            kotlin.srcDir("src/jvm/test")

            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.6.2")
                implementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
            }
        }

        val iosMain by getting {
            kotlin.srcDir("src/ios/main")

            dependencies {
                implementation(kotlin("stdlib-native"))
                implementation("org.jetbrains.kotlinx:kotlinx-io-native:$kotlinxIoVersion")
                api("io.ktor:ktor-client-ios:$ktorVersion")
                api("io.ktor:ktor-client-json-native:$ktorVersion")
                api("io.ktor:ktor-client-serialization-native:$ktorVersion")
            }
        }

        val iosTest by getting {
            kotlin.srcDir("src/ios/test")

            dependencies {
                implementation("io.ktor:ktor-client-mock-native:$ktorVersion")
            }
        }

        val jsMain by getting {
            kotlin.srcDir("src/js/main")

            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-io-js:$kotlinxIoVersion")
                api("io.ktor:ktor-client-js:$ktorVersion")
                api("io.ktor:ktor-client-json-js:$ktorVersion")
                api("io.ktor:ktor-client-serialization-js:$ktorVersion")
            }
        }

        val jsTest by getting {
            kotlin.srcDir("src/js/test")

            dependencies {
                implementation("io.ktor:ktor-client-mock-js:$ktorVersion")
            }
        }
    }
}
