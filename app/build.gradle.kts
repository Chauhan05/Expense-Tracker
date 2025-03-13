plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.0.21-1.0.25"
}

android {
    namespace = "com.example.expensetracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.expensetracker"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Compose integration for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.ui) // Replace with the latest version
    implementation(libs.material3) // or the latest version
    implementation(libs.ui.tooling.preview) // or the latest version
    implementation(libs.androidx.room.ktx)
    implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha")
    val nav_version = "2.8.6"

    implementation("androidx.navigation:navigation-compose:$nav_version")

//    For view Model

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")



    implementation("com.google.accompanist:accompanist-flowlayout:0.33.2-alpha")


}
