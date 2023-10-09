package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions

class GetCurrentBranchNameTask extends DefaultTask{

    @TaskAction
    def identifyBranchName() {
        def branchName = 'git symbolic-ref --short HEAD'.execute().text.trim()
        GitBranchTaggingExtensions gitExtensions = GitBranchTaggingExtensions.getInstance()
        gitExtensions.setCurrentBranch(branchName)
        println(branchName)
    }
}
