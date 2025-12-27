plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.we.instagram"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.we.instagram"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
    buildFeatures {
        viewBinding = true
        dataBinding = false
    }
}

dependencies {

    // ---- Core Android ----
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)

    // ---- Navigation ----
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ---- Networking ----
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.moshi.kotlin)

//    implementation(libs.kotlinStdlib)
//    implementation(libs.kotlinCoroutinesCore)
//    implementation(libs.kotlinCoroutinesAndroid)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    implementation(libs.moshi)
    kapt(libs.moshi.kotlin.codegen)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // ---- Room Database ----
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // ---- Coroutines ----
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // ---- Lifecycle (ViewModel / LiveData / Flow) ----
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // ---- RecyclerView ----
    implementation(libs.recyclerview)

    // ---- Image Loading ----
    implementation(libs.coil)

    // ---- ExoPlayer (Bonus / Reels) ----
    implementation(libs.exoplayer.core)
    implementation(libs.exoplayer.ui)

    // ---- Testing ----
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.arch.core.testing)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}