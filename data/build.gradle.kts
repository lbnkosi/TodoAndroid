plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    Libs.apply {

        //Kotlin
        implementation(ANDROID_CORE)
        implementation(KOTLIN_COROUTINES)
        implementation(KOTLIN_STANDARD_LIB)
        implementation(KOTLIN_COROUTINES_ANDROID)

        //Room
        kapt(ROOM_COMPILER)
        implementation(ROOM_KTX)
        implementation(ROOM_RUNTIME)
        testImplementation(ROOM_TESTING)

        //Unit testing
        testImplementation(JUNIT)
        androidTestImplementation(ESPRESSO)
        androidTestImplementation(ANDROID_TEST_JUNIT)

        //Hilt
        kapt(HILT_COMPILER)
        implementation(HILT_ANDROID)

        //Stream Support
        implementation(STREAM_SUPPORT)

        //Timber
        implementation(TIMBER)
    }

    implementation(project(":domain"))
}