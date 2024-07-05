plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = CoreUiConfig.nameSpace
    compileSdk = CoreUiConfig.compileSdk

    defaultConfig {
        minSdk = CoreUiConfig.minSdk

        testInstrumentationRunner = CoreUiConfig.testInstrumentationRunner
        consumerProguardFiles(CoreUiConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = CoreUiConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(CoreUiConfig.proguardFileTxt),
                CoreUiConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = CoreUiConfig.sourceCompatibility
        targetCompatibility = CoreUiConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = CoreUiConfig.jvmTarget
    }
}

dependencies {

    // Modules
    implementation(project(Modules.CORE))

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

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}