package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions

class DefineBuildVersionTask extends DefaultTask{

    @TaskAction
    def defineVersion() {
        GitBranchTaggingExtensions gitExtensions = GitBranchTaggingExtensions.getInstance()
        def branchName = gitExtensions.currentBranch
        def lastPublishedTag = gitExtensions.lastTag


        def buildVersion = determineVersion(branchName, lastPublishedTag)
        gitExtensions.setBuildVersion(buildVersion)
        println(buildVersion)
    }

    private static def determineVersion(branchName, lastPublishedTag) {
        def versionSegments = lastPublishedTag.toString().tokenize(/v(\d+)\.(\d+)(?:-(\w+))?/)
        def major = versionSegments[0] as Integer
        def minor = versionSegments[1] as Integer

        switch (branchName) {
            case 'master':
                major++
                minor = 0
                break
            case 'main':
                major++
                minor = 0
                break
            case 'stage':
                minor++
                return "v${major}.${minor}-rc"
            case 'dev':
                minor++
                break
            case 'qa':
                minor++
                break
            default:
                return "v${major}.${minor}-SNAPSHOT"
        }

        return "v${major}.${minor}"
    }
}
