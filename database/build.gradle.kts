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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
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
    implementation(libs.androidx.junit.ktx)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin.bundle)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.ksp)

    // Tests
    testImplementation(libs.androidx.runner)
    testImplementation(libs.junit)
    testImplementation(libs.core.ktx)
    testImplementation(libs.kotlinx.coroutines.test)
    //testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}