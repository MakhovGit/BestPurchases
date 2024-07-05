plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = AddItemScreenConfig.nameSpace
    compileSdk = AddItemScreenConfig.compileSdk

    defaultConfig {
        minSdk = AddItemScreenConfig.minSdk

        testInstrumentationRunner = AddItemScreenConfig.testInstrumentationRunner
        consumerProguardFiles(AddItemScreenConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = AddItemScreenConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(AddItemScreenConfig.proguardFileTxt),
                AddItemScreenConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = AddItemScreenConfig.sourceCompatibility
        targetCompatibility = AddItemScreenConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = AddItemScreenConfig.jvmTarget
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