import Compose.compose_version

object Deps {


    // Coil
    private const val coil_version = "1.4.0"
    const val coil = "io.coil-kt:coil-compose:$coil_version"

    // LiveData
    private const val livedata_version = "1.2.0-beta01"
    const val livedata = "androidx.compose.runtime:runtime-livedata:1.2.0-beta01"
}

object Tests {
    const val extJunit = "androidx.test.ext:junit:1.1.3"
    const val expresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val composeUi =  "androidx.compose.ui:ui-test-junit4:$compose_version"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$compose_version"
}

object Compose {

    const val compose_version = "1.1.1"
    const val compose = "androidx.compose.ui:ui:$compose_version"
    const val composeMaterial = "androidx.compose.material:material:$compose_version"
    const val composeUi = "androidx.compose.ui:ui-tooling:$compose_version"

    const val extraIcons = "androidx.compose.material:material-icons-extended:$compose_version"

    private const val activity_version = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activity_version"

    private const val constraint_version = "1.1.0-alpha01"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha01"

}