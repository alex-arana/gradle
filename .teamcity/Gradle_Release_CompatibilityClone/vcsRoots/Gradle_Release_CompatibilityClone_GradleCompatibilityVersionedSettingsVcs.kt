package Gradle_Release_CompatibilityClone.vcsRoots

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.vcs.GitVcsRoot

object Gradle_Release_CompatibilityClone_GradleCompatibilityVersionedSettingsVcs : GitVcsRoot({
    uuid = "9c75728d-6a67-407d-9266-ac4785ec433b"
    extId = "Gradle_Release_CompatibilityClone_GradleCompatibilityVersionedSettingsVcs"
    name = "Gradle Compatibility Versioned Settings VCS"
    url = "git@github.com:gradle/gradle.git"
    branch = "ew_compatibility"
    authMethod = uploadedKey {
        uploadedKey = "id_rsa_gradlewaregitbot"
    }
})
