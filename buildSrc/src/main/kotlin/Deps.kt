object Deps {
    private const val androidxCore_version = "1.7.0"
    const val androidxCore = "androidx.core:core-ktx:$androidxCore_version"
    
    private const val appCompat_version = "1.4.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompat_version"
    
    private const val material_version = "1.6.0"
    const val material = "com.google.android.material:material:$material_version"
    
    private const val compose_version = "1.1.1"
    const val compose = "androidx.compose.ui:ui:$compose_version"
    const val composeMaterial = "androidx.compose.material:material:$compose_version"
    const val composeUi = "androidx.compose.ui:ui-tooling:$compose_version"

    private const val lifecycle_version = "2.4.1"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"

    private const val activity_version = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activity_version"

    // Compose dependencies

    // LiveData
    private const val livedata_version = "1.2.0-beta01"
    const val livedata = "androidx.compose.runtime:runtime-livedata:1.2.0-beta01"

    // ConstraintLayout
    private const val constraint_version = "1.1.0-alpha01"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha01"

    // view model


    // navigation
    private const val navigation_version = "2.4.0-beta01"
    const val navigation = "androidx.navigation:navigation-compose:$navigation_version"
    private const val hilt_nav_version = "1.0.0"
    const val hiltNav = "androidx.hilt:hilt-navigation-compose:$hilt_nav_version"

    // extra icons
    const val extraIcons = "androidx.compose.material:material-icons-extended:$compose_version"

    // Coroutines
    private const val coroutines_version = "1.6.0"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    //Dagger - Hilt
    private const val hilt_version = "2.42"
    const val hilt = "com.google.dagger:hilt-android:$hilt_version"
    const val hiltGoogleCompiler = "com.google.dagger:hilt-android-compiler:$hilt_version"
    private const val hilt_viewmodel_version = "1.0.0-alpha03"
    const val hiltViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_viewmodel_version"
    private const val hilt_compiler_version = "1.0.0"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"

    // Room
    private const val room_version = "2.4.2"
    const val roomRuntime = "androidx.room:room-runtime:$room_version"
    const val roomCompiler = "androidx.room:room-compiler:$room_version"
    const val roomExtensions = "androidx.room:room-ktx:$room_version"

    // Coil
    private const val coil_version = "1.4.0"
    const val coil = "io.coil-kt:coil-compose:$coil_version"

    // Retrofit
    private const val retrofit_version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"

}