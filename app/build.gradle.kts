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

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.room:room-runtime:2.2.6")
    annotationProcessor("androidx.room:room-compiler:2.2.6")

    Libs.apply {
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

        //Android Room/DB debug
        debugImplementation(ANDROID_DEBUG)

        //Stream Support
        implementation(STREAM_SUPPORT)
    }

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}