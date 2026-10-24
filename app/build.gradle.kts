plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "net.sharksystem.sharkpki_gui"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "net.sharksystem.sharkpki_gui"
        minSdk = 34
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(files("libs/ASAPAndroid.aar"))
    implementation(files("libs/SharkPKI.jar"))
    implementation(files("libs/SharkPeer.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}