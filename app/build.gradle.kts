plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.looker.howlmusic"
        minSdk = 29
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
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
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}

dependencies {

    //Activity
    implementation("androidx.activity:activity-compose:1.3.1")

    //Accompanist
    implementation("com.google.accompanist:accompanist-insets:0.15.0")
    implementation("com.google.accompanist:accompanist-insets-ui:0.15.0")

    //Coil
    implementation("io.coil-kt:coil-compose:1.3.1")

    //Core
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1-native-mt")

    //Exoplayer
    implementation("com.google.android.exoplayer:exoplayer-core:2.14.2")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-alpha03")

    //Material
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.material:material:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra["compose_version"]}")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")

    //Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    //UI
    implementation("androidx.compose.ui:ui:1.0.1")
    implementation("androidx.compose.ui:ui-tooling:1.0.1")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha03")

}