buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.classpathGradle)
        classpath(Libraries.classpathKotlin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}