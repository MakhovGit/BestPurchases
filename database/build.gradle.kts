plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = DatabaseConfig.nameSpace
    compileSdk = DatabaseConfig.compileSdk

    defaultConfig {
        minSdk = DatabaseConfig.minSdk

        testInstrumentationRunner = DatabaseConfig.testInstrumentationRunner
        consumerProguardFiles(DatabaseConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = DatabaseConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(DatabaseConfig.proguardFileTxt),
                DatabaseConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = DatabaseConfig.sourceCompatibility
        targetCompatibility = DatabaseConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = DatabaseConfig.jvmTarget
    }
}

dependencies {

    // Modules
    implementation(project(Modules.CORE))

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin.bundle)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.ksp)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}