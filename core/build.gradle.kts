plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = CoreConfig.nameSpace
    compileSdk = CoreConfig.compileSdk

    defaultConfig {
        minSdk = CoreConfig.minSdk

        testInstrumentationRunner = CoreConfig.testInstrumentationRunner
        consumerProguardFiles(CoreConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = CoreConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(CoreConfig.proguardFileTxt),
                CoreConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = CoreConfig.sourceCompatibility
        targetCompatibility = CoreConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = CoreConfig.jvmTarget
    }
}

dependencies {

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}