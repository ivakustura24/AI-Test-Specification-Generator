plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("plugin.serialization") version "1.6.0"
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "hr.foi.database"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.4.7")
    implementation("io.ktor:ktor-client-cio:2.3.6")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    val livedata_version = "2.2.0-alpha02"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$livedata_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$livedata_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$livedata_version")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


}