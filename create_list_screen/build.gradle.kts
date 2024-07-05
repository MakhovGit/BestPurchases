plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = CreateListScreenConfig.nameSpace
    compileSdk = CreateListScreenConfig.compileSdk

    defaultConfig {
        minSdk = CreateListScreenConfig.minSdk

        testInstrumentationRunner = CreateListScreenConfig.testInstrumentationRunner
        consumerProguardFiles(CreateListScreenConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = CreateListScreenConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(CreateListScreenConfig.proguardFileTxt),
                CreateListScreenConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = CreateListScreenConfig.sourceCompatibility
        targetCompatibility = CreateListScreenConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = CreateListScreenConfig.jvmTarget
    }
}

dependencies {

    // Modules
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.DATABASE))

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin.bundle.full)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}