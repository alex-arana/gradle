package Gradle_Branches_CoveragePhase_LinuxCoverage_LinuxJava17ParallelIntegrationTests.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.GradleBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object Gradle_Branches_CoveragePhase_LinuxCoverage_LinuxJava17ParallelIntegrationTest_7 : BuildType({
    uuid = "b832521f-f0ff-4104-8dc5-5b4e74b79ebb"
    extId = "Gradle_Branches_CoveragePhase_LinuxCoverage_LinuxJava17ParallelIntegrationTest_7"
    name = "(7) Linux - Java 1.7 - Parallel integration tests"
    description = "Parallel integration tests for Linux [clean parallelTest7]"

    artifactRules = """**/build/reports/** => reports
subprojects/*/build/tmp/test files/** => test-files
build/daemon/** => daemon
intTestHomeDir/worker-1/daemon/**/*.log => intTestHome-daemon
build/errorLogs/** => errorLogs"""
    maxRunningBuilds = 3

    vcs {
        root("Gradle_Branches_GradlePersonalBranches")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    steps {
        gradle {
            name = "GRADLE_RUNNER"
            tasks = "clean parallelTest7"
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
            executionMode = BuildStep.ExecutionMode.ALWAYS
            scriptContent = """REPO=/home/%env.USER%/.m2/repository
if [ -e ${'$'}REPO ] ; then
rm -rf ${'$'}REPO
echo "${'$'}REPO was polluted during the build"
return 1
else
echo "${'$'}REPO does not exist"
fi"""
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
        executionTimeoutMin = 480
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
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_1LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_2LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_3LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_4LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_5LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_6LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_7LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
        dependency(Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18.buildTypes.Gradle_Branches_CommitPhase_LinuxCommit_LinuxCommitJava18_8LinuxCommitJava18) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }
        }
    }

    requirements {
        contains("teamcity.agent.jvm.os.name", "Linux")
    }
})
