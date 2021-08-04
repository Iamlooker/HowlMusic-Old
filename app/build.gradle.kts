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
            isMinifyEnabled = false
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
    implementation("androidx.activity:activity-compose:1.3.0")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.15.0")
    implementation("com.google.accompanist:accompanist-insets:0.15.0")
    implementation("com.google.accompanist:accompanist-insets-ui:0.15.0")

    //Coil
    implementation("io.coil-kt:coil-compose:1.3.1")

    //Core
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1-native-mt")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0-rc02")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-alpha02")

    //Material
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra["compose_version"]}")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha05")

    //Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    //UI
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling:1.0.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02")

}