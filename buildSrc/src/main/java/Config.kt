object AndroidSdk {
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30
    const val applicationId = "com.dindinn.app"
    const val versionCode = 1
    const val versionName = "0.1"
}

object BuildPlugins {

    object Versions {
        const val gradle_version = "7.0.1"
        const val kotlin_version = "1.5.21"
        const val hilt_versions_version = "2.37"
    }

    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val hilt_versions_plugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_versions_version}"
}


object Libs {

    object Versions {
        const val core_ktx_version = "1.0.2"
        const val activity_ktx_version = "1.3.1"
        const val fragment_ktx_version = "1.3.6"

        const val appcompat_version = "1.1.0"
        const val constraint_version = "1.1.3"
        const val navigation_version = "2.0.0"
        const val material_version = "1.1.0-alpha09"
        const val swipe_version = "1.1.0"
        const val databinding_common_version = "7.0.2"

        const val retrofit_version = "2.6.2"

        const val okhttp_version = "4.9.0"
        const val gson_version = "2.8.6"
        const val logging_interceptor_version = "4.9.0"

        const val rxandroid_version = "2.1.1"
        const val rxjava_version = "2.2.9"
        const val rxjava_adapter_version = "2.9.0"

        const val hilt_version = "2.35.1"
        const val hilt_compiler_version = "2.35.1"

        const val hilt_viewmodel_version = "1.0.0-alpha03"
        const val hilt_viewmodel_compiler_version = "1.0.0"

        const val glide_version = "4.12.0"
        const val glide_compiler_version = "4.12.0"

        const val multidex_version = "2.0.1"
    }

    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val swipe_refresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_version}"
    const val databinding_common = "androidx.databinding:databinding-runtime:${Versions.databinding_common_version}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"

    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava_version}"
    const val rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxjava_adapter_version}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid_version}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_compiler_version}"

    const val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel_version}"
    const val hilt_viewmodel_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_viewmodel_compiler_version}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler_version}"

    const val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"

}

object TestLibs {

    object Versions {
        const val junit_version = "4.12"
        const val test_runner_version = "1.2.0"
        const val espresso_version = "3.2.0"
        const val androidx_core_testing_version = "2.1.0"
        const val google_truth_version = "1.0"
        const val mockito_version = "3.1.0"
        const val mockito_kotlin_version = "2.1.0"
    }

    const val junit = "junit:junit:${Versions.junit_version}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    const val androidx_core_testing =
        "androidx.arch.core:core-testing:${Versions.androidx_core_testing_version}"
    const val google_truth = "com.google.truth:truth:${Versions.google_truth_version}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_version}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_version}"
    const val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"
}