package Gradle_Release_CompatibilityClone

import Gradle_Release_CompatibilityClone.buildTypes.*
import Gradle_Release_CompatibilityClone.vcsRoots.*
import Gradle_Release_CompatibilityClone.vcsRoots.Gradle_Release_CompatibilityClone_GradleCompatibilityVersionedSettingsVcs
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings

object Project : Project({
    uuid = "6a760f70-09fc-4d3a-85f2-762c359c97e0"
    extId = "Gradle_Release_CompatibilityClone"
    parentId = "Gradle_Release"
    name = "Compatibility Clone"
    description = "runs compatibility checks to ensure gradle is working with other systems"

    vcsRoot(Gradle_Release_CompatibilityClone_GradleCompatibilityVersionedSettingsVcs)

    buildType(Gradle_Release_CompatibilityClone_ApiChangeReport)
    buildType(Gradle_Release_CompatibilityClone_ColonyCompatibility)

    features {
        versionedSettings {
            id = "PROJECT_EXT_63"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = Gradle_Release_CompatibilityClone_GradleCompatibilityVersionedSettingsVcs.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
        }
    }
})
