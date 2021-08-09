plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30

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
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

dependencies {
    implementation(Libraries.activity)
    implementation(Libraries.insets)
    implementation(Libraries.insetsUi)
    implementation(Libraries.coil)
    implementation(Libraries.core)
    implementation(Libraries.appcompat)
    implementation(Libraries.coroutines)
    implementation(Libraries.exoplayer)
    implementation(Libraries.lifecycleLiveData)
    implementation(Libraries.materialCompose)
    implementation(Libraries.materialIconExtended)
    implementation(Libraries.navigation)
    implementation(Libraries.palette)
    implementation(Libraries.composeUi)
    implementation(Libraries.composeUiTooling)
    implementation(Libraries.lifecycleViewModel)
}