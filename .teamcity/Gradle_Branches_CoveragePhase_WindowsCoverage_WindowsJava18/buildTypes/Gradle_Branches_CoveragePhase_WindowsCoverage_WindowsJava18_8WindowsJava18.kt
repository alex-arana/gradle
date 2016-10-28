package Gradle_Branches_CoveragePhase_WindowsCoverage_WindowsJava18.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object Gradle_Branches_CoveragePhase_WindowsCoverage_WindowsJava18_8WindowsJava18 : BuildType({
    uuid = "a805723e-d623-4a50-8baf-01f0db7db978"
    extId = "Gradle_Branches_CoveragePhase_WindowsCoverage_WindowsJava18_8WindowsJava18"
    name = "(8) Windows - Java 1.8"
    description = "Full platform coverage for Windows through forked tests [clean platformTest8]"

    artifactRules = """**/build/reports/** => reports
subprojects/*/build/tmp/test files/** => test-files
build/daemon/** => daemon
intTestHomeDir/worker-1/daemon/**/*.log => intTestHome-daemon
build/errorLogs/** => errorLogs"""
    maxRunningBuilds = 3

    params {
        param("env.JAVA_HOME", "%windows.java8.oracle.64bit%")
    }

    vcs {
        root("Gradle_Branches_GradlePersonalBranches")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    steps {
        gradle {
            name = "GRADLE_RUNNER"
            tasks = "clean platformTest8"
            gradleParams = "-I ./gradle/buildScanInit.gradle -PtimestampedVersion -PmaxParallelForks=%maxParallelForks% -s --no-daemon --continue"
            useGradleWrapper = true
        }
        gradle {
            name = "VERIFY_TEST_FILES_CLEANUP"
            tasks = "verifyTestFilesCleanup"
            gradleParams = "-I ./gradle/buildScanInit.gradle -PtimestampedVersion -PmaxParallelForks=%maxParallelForks% -s --no-daemon --continue"
            useGradleWrapper = true
        }
        script {
            name = "CHECK_CLEAN_M2"
            scriptContent = """IF exist %teamcity.agent.jvm.user.home%\.m2\repository (
    RMDIR /S /Q %teamcity.agent.jvm.user.home%\.m2\repository
    EXIT 1
)"""
        }
        gradle {
            name = "TAG_BUILD"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            tasks = "tagBuild"
            buildFile = "gradle/buildTagging.gradle"
            gradleParams = "-PteamCityUsername=%teamcity.username.restbot% -PteamCityPassword=%teamcity.password.restbot% -PteamCityBuildId=%teamcity.build.id% -PgithubToken=%github.ci.oauth.token%"
            useGradleWrapper = true
        }
    }

    failureConditions {
        executionTimeoutMin = 540
    }

    dependencies {
        dependency(Gradle_Branches_CommitPhase.buildTypes.Gradle_Branches_CommitPhase_BuildDistributions) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                cleanDestination = true
                artifactRules = """distributions/*-all.zip => incoming-distributions
        build-receipt.properties => incoming-distributions"""
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_1WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_2WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_3WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_4WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_5WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_6WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_7WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17.buildTypes.Gradle_Branches_CommitPhase_WindowsCommit_WindowsCommitJava17_8WindowsCommitJava) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
    }

    requirements {
        contains("teamcity.agent.jvm.os.name", "Windows")
    }
})
