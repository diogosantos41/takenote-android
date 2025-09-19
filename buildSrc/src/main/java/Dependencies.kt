object Versions {
    const val jvmTarget = "17"
    const val kotlin_compiler_extension = "1.5.14"
    const val google_services = "4.4.2"
    const val dagger_hilt = "2.52"
    const val dagger_hilt_compiler = "2.52"
    const val hilt_compiler = "1.2.0"
    const val core_ktx = "1.9.0"
    const val compose = "1.5.4"
    const val compose_activity = "1.9.2"
    const val compose_navigation = "2.7.0"
    const val compose_material3 = "1.2.1"
    const val compose_hilt_navigation = "1.0.0"
    const val compose_viewmodel_lifecycle = "2.5.0-rc02"
    const val lifecycle_runtime_ktx = "2.5.0"
    const val splash_screen = "1.0.0"
    const val navigation_animation = "0.34.0"
    const val coroutines = "1.6.4"
    const val room = "2.6.1"
    const val datastore = "1.0.0"
    const val firebase_analytics = "21.1.0"
    const val firebase_crashlytics = "18.2.11"
    const val firebase_crashlytics_gradle = "3.0.2"
    const val leak_canary_version = "2.9.1"

}

object App {
    const val applicationId = "com.dscoding.takenoteapp"
    const val versionCode = 7
    const val versionName = "1.3.2"
}
object AndroidSdk {
    const val minSdk = 28
    const val compileSdk = 36
    const val targetSdk = 36
}

object Classpaths {
    const val hilt_android = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt}"
    const val google_services = "com.google.gms:google-services:${Versions.google_services}"
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebase_crashlytics_gradle}"
}

object AndroidX {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val compose_ui_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val compose_viewmodel_lifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_viewmodel_lifecycle}"
    const val compose_navigation =
        "androidx.navigation:navigation-compose:${Versions.compose_navigation}"
    const val compose_material_icons =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val compose_material3 =
        "androidx.compose.material3:material3:${Versions.compose_material3}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx}"
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.splash_screen}"
}

object Accompanist {
    const val navigation_animation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.navigation_animation}"
}

object SquareUp {
    const val leak_canary =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary_version}"
}

object Coroutines {
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object DependencyInjection {
    const val dagger_hilt_android = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    const val dagger_hilt_android_compiler =
        "com.google.dagger:hilt-compiler:${Versions.dagger_hilt_compiler}"
    const val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.compose_hilt_navigation}"
}

object Data {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
}

object Firebase {
    const val analytics =
        "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"
    const val crashlytics =
        "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebase_crashlytics}"
}

object AnnotationProcessing {
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler}"
}

object Testing {
    // Local unit tests
    const val test_core = "androidx.test:core:1.4.0"
    const val junit = "junit:junit:4.13.2"
    const val arch_core_testing = "androidx.arch.core:core-testing:2.1.0"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val google_truth = "com.google.truth:truth:1.1.3"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:4.9.1"
    const val mockk = "io.mockk:mockk:1.10.5"

    // Instrumentation tests
    const val hilt_android_testing = "com.google.dagger:hilt-android-testing:2.52"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:2.52"
    const val ext_junit = "androidx.test.ext:junit:1.1.3"
    const val test_core_ktx = "androidx.test:core-ktx:1.4.0"
    const val mockk_android = "io.mockk:mockk-android:1.10.5"
    const val test_runner = "androidx.test:runner:1.4.0"
    const val composes_ui_manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val compose_ui_test_junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

}

