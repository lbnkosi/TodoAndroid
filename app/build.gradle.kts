plugins {
    AppConfig.apply {
        id(APPLICATION)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
        id(HILT_ANDROID_PLUGIN)
        id("kotlin-parcelize")
    }
}

android {
    AppConfig.apply {
        compileSdkVersion(COMPILE_SDK_VERSION)
        buildToolsVersion(BUILD_TOOLS_VERSION)

        defaultConfig {
            applicationId = APPLICATION_ID
            minSdkVersion(MIN_SDK_VERSION)
            targetSdkVersion(TARGET_SDK_VERSION)
            versionCode = VERSION_CODE
            versionName = VERSION_NAME

            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        }

        buildTypes {
            getByName(BUILD_TYPE) {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile(PROGUARD_FILE), PROGUARD_RULES)
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions { jvmTarget = "1.8" }

        buildFeatures { dataBinding = true }

        lintOptions { isCheckReleaseBuilds = false }

        hilt { enableExperimentalClasspathAggregation = true }

    }
}

dependencies {
    Libs.apply {

        //Android/Google/Kotlin
        implementation(MATERIAL)
        implementation(APP_COMPAT)
        implementation(VIEW_PAGER_2)
        implementation(ANDROID_CORE)
        implementation(SWIPE_REFRESH)
        implementation(CONSTRAINT_LAYOUT)
        implementation(KOTLIN_STANDARD_LIB)
        implementation(KOTLIN_COROUTINES_ANDROID)

        //Hilt
        kapt(HILT_COMPILER)
        implementation(HILT_ANDROID)
        kapt(HILT_ANDROID_X_COMPILER)
        implementation(HILT_VIEWMODEL)

        //Arch components
        implementation(FRAGMENT_KTX)
        implementation(LIFECYCLE_COMMON)
        implementation(LIFECYCLE_RUNTIME)
        implementation(LIFECYCLE_LIVEDATA)
        implementation(LIFECYCLE_VIEWMODEL)
        implementation(LIFECYCLE_EXTENSIONS)
        implementation(LIFECYCLE_COMMON_JAVA_8)

        //Room
        annotationProcessor(ROOM_COMPILER)
        implementation(ROOM_KTX)
        implementation(ROOM_RUNTIME)
        testImplementation(ROOM_TESTING)

        //Junit expresso
        testImplementation(JUNIT)
        androidTestImplementation(ESPRESSO)
        androidTestImplementation(ANDROID_TEST_JUNIT)

        //Android Room/DB debug
        debugImplementation(ANDROID_DEBUG)

        //Stream Support
        implementation(STREAM_SUPPORT)

        //Lottie support
        implementation(LOTTIE)
    }
    implementation(project(":data"))
    implementation(project(":domain"))
}