import com.rick.budgetly.buildsrc.Libs
import com.rick.budgetly.buildsrc.Modules

apply {
    from("$rootDir/kotlin-library.gradle")
}

dependencies {

    "implementation" (project(Modules.core))

    "implementation" (Libs.AndroidX.Compose.activityCompose)
    "implementation" (Libs.AndroidX.Compose.toolingPrev)
    "debugImplementation" (Libs.AndroidX.Compose.tooling)

    "implementation" (Libs.AndroidX.Compose.material)
    "implementation" (Libs.AndroidX.Compose.extraIcons)
}