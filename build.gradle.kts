buildscript {

    val compose_version by extra("1.0.1")
    val lifecycle_version by extra("2.3.1")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}