package com.rick.budgetly.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.1"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"

    const val googleMaterial = "com.google.android.material:material:1.6.1"

    object Coroutines {
        private const val version = "1.6.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {

        private const val androidxCore_version = "1.7.0"
        const val coreKtx = "androidx.core:core-ktx:$androidxCore_version"

//        private const val appCompat_version = "1.4.1"
//        const val appCompat = "androidx.appcompat:appcompat:$appCompat_version"

        object Compose {
            private const val activity_version = "1.4.0"
            const val activityCompose = "androidx.activity:activity-compose:$activity_version"

            const val compose_ui_version = "1.1.1"

            const val compose = "androidx.compose.ui:ui:$compose_ui_version"
            const val toolingPrev = "androidx.compose.ui:ui-tooling-preview:$compose_ui_version"
            const val material = "androidx.compose.material:material:$compose_ui_version"
            const val extraIcons = "androidx.compose.material:material-icons-extended:$compose_ui_version"

            const val tooling = "androidx.compose.ui:ui-tooling:$compose_ui_version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${compose_ui_version}"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$compose_ui_version"
        }

        object ConstraintLayout {
            const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
        }

        object Lifecycle {
            private const val version = "2.4.1"
//            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
//            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object Navigation {
            private const val version = "2.4.2"
            const val navigation = "androidx.navigation:navigation-compose:$version"
        }

    }

    object Hilt {
        const val hilt_version = "2.42"
        const val hilt = "com.google.dagger:hilt-android:$hilt_version"
        const val compiler = "com.google.dagger:hilt-compiler:$hilt_version"

        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"

        const val androidTest = "com.google.dagger:hilt-android-testing:$hilt_version"
        const val kaptAndroid = "com.google.dagger:hilt-compiler:$hilt_version"

        const val test = "com.google.dagger:hilt-android-testing:$hilt_version"
        const val kaptTest = "com.google.dagger:hilt-compiler:$hilt_version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.1.0"
    }

    object Tests {
        const val junit = "junit:junit:4.13.2"
        const val extJunit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}






