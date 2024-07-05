plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = ListScreenConfig.nameSpace
    compileSdk = ListScreenConfig.compileSdk

    defaultConfig {
        minSdk = ListScreenConfig.minSdk

        testInstrumentationRunner = ListScreenConfig.testInstrumentationRunner
        consumerProguardFiles(ListScreenConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = ListScreenConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(ListScreenConfig.proguardFileTxt),
                ListScreenConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = ListScreenConfig.sourceCompatibility
        targetCompatibility = ListScreenConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = ListScreenConfig.jvmTarget
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