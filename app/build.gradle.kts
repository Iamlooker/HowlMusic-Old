plugins {
    id("com.android.application")
    id("kotlin-android")
}

val insetsVersion by extra("0.16.1")
val composeVersion by extra("1.0.1")
val lifecycleVersion by extra("2.3.1")
val exoplayerVersion by extra("2.15.0")

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.looker.howlmusic"
        minSdk = 29
        targetSdk = 30
        versionCode = 1
        versionName = "Pegasus"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        debug {
            isMinifyEnabled = true
            applicationIdSuffix = ".debug"
        }
        all {
            isCrunchPngs = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {

    //Activity
    implementation("androidx.activity:activity-compose:1.3.1")

    //Accompanist
    implementation("com.google.accompanist:accompanist-insets:$insetsVersion")

    //Coil
    implementation("io.coil-kt:coil-compose:1.3.2")

    //Core
    implementation("androidx.core:core-ktx:1.6.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    //Exoplayer
    implementation("com.google.android.exoplayer:exoplayer:$exoplayerVersion")
    implementation("com.google.android.exoplayer:extension-mediasession:$exoplayerVersion")

    //Material
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")

    //Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    //UI
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

}
