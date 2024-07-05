plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = NavigationConfig.nameSpace
    compileSdk = NavigationConfig.compileSdk

    defaultConfig {
        minSdk = NavigationConfig.minSdk

        testInstrumentationRunner = NavigationConfig.testInstrumentationRunner
        consumerProguardFiles(NavigationConfig.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = NavigationConfig.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(NavigationConfig.proguardFileTxt),
                NavigationConfig.proguardFilePro
            )
        }
    }
    compileOptions {
        sourceCompatibility = NavigationConfig.sourceCompatibility
        targetCompatibility = NavigationConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = NavigationConfig.jvmTarget
    }
}

dependencies {

    // Modules
    implementation(project(Modules.CORE))
    implementation(project(Modules.HOME_SCREEN))
    implementation(project(Modules.CREATE_LIST_SCREEN))
    implementation(project(Modules.LIST_SCREEN))
    implementation(project(Modules.ADD_ITEM_SCREEN))

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
    implementation(libs.bundles.koin.bundle.full)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}