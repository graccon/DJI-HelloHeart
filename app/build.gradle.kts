import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("org.jetbrains.kotlin.kapt")
}

val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}


android {
    namespace = "com.example.helloheart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.helloheart"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val DjiApiKey = localProperties.getProperty("DJI_API_KEY")
        buildConfigField("String", "DJI_API_KEY", "\"${DjiApiKey}\"")
        manifestPlaceholders["DJI_API_KEY"] = DjiApiKey
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
        buildConfig = true
    }
    packaging {
        jniLibs {
            keepDebugSymbols.add("**/libdjivideo.so")
            keepDebugSymbols.add("**/libSDKRelativeJNI.so")
            keepDebugSymbols.add("**/libFlyForbid.so")
            keepDebugSymbols.add("**/libduml_vision_bokeh.so")
            keepDebugSymbols.add("**/libyuv2.so")
            keepDebugSymbols.add("**/libGroudStation.so")
            keepDebugSymbols.add("**/libFRCorkscrew.so")
            keepDebugSymbols.add("**/libUpgradeVerify.so")
            keepDebugSymbols.add("**/libFR.so")
            keepDebugSymbols.add("**/libDJIFlySafeCore.so")
            keepDebugSymbols.add("**/libdjifs_jni.so")
            keepDebugSymbols.add("**/libsfjni.so")
            keepDebugSymbols.add("**/libDJICommonJNI.so")
            keepDebugSymbols.add("**/libDJICSDKCommon.so")
            keepDebugSymbols.add("**/libDJIUpgradeCore.so")
            keepDebugSymbols.add("**/libDJIUpgradeJNI.so")
        }
        resources {
            excludes += "META-INF/rxjava.properties"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // DJI SDK dependencies
    implementation("com.dji:dji-sdk:4.16")
    compileOnly("com.dji:dji-sdk-provided:4.16")

    // Additional dependencies
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.squareup:otto:1.3.8")
    implementation("com.google.android.material:material:1.5.0")

    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.annotation:annotation:1.3.0")
    implementation("com.jakewharton:butterknife:10.2.3")
    kapt("com.jakewharton:butterknife-compiler:10.2.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-native-mt")
}