plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myjogadores"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myjogadores"
        minSdk = 33
        targetSdk = 35
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

// Navigation para Jetpack Compose
    implementation ("androidx.navigation:navigation-compose:2.7.7")
// Biblioteca Gson para serialização/deserialização JSON
    implementation ("com.google.code.gson:gson:2.10.1")

    // COIL - Carregamento de imagens com Compose
    implementation("io.coil-kt:coil-compose:2.4.0")

    // FOUNDATION - Necessário para LazyVerticalGrid, clip, etc.
    implementation("androidx.compose.foundation:foundation:1.5.0")

    // VIEWMODEL COMPOSE - Para usar ViewModel em Composables
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // COROUTINES - Para fluxo assíncrono (StateFlow, delay, etc.)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

// Retrofit com Moshi (conversor JSON)
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

// Moshi
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")




    // ============================================

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
