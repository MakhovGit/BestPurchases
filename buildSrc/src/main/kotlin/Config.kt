import org.gradle.api.JavaVersion

abstract class BaseConfig {
    open val compileSdk = 34
    open val minSdk = 24
    open val targetSdk = 34
    open val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    open val isMinifyEnabled = false
    open val consumerRules = "consumer-rules.pro"
    open val proguardFileTxt = "proguard-android-optimize.txt"
    open val proguardFilePro = "proguard-rules.pro"
    open val sourceCompatibility = JavaVersion.VERSION_17
    open val targetCompatibility = JavaVersion.VERSION_17
    open val jvmTarget = "17"
}

object AppConfig : BaseConfig() {
    const val nameSpace = "org.purchases.best"
    const val applicationId = "org.purchases.best"
    const val versionCode = 1
    const val versionName = "1.0"
    const val useSupportLibrary = true
    const val compose = true
    const val kotlinCompilerExtensionVersion = "1.5.14"
    const val excludes = "/META-INF/{AL2.0,LGPL2.1}"
}

object CoreConfig : BaseConfig() {
    const val nameSpace = "org.purchases.core"
}

object DatabaseConfig : BaseConfig() {
    const val nameSpace = "org.purchases.database"
}