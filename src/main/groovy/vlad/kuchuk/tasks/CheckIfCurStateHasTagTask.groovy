package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions

class CheckIfCurStateHasTagTask extends DefaultTask{

    @TaskAction
    def checkIfCurStateAlreadyTagged() {
        GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()

        String lastCommitHash = 'git log -1 --pretty=format:"%H"'.execute().text.trim()
        String lastTaggedCommand = 'git show ' + extensions.getLastTag() + ' --pretty=format:"%H"'
        String lastTaggedCommitHash = lastTaggedCommand.execute().text.trim().split('\n')[0]

        if (lastCommitHash == lastTaggedCommitHash) {
            println('skipped')
            extensions.setAlreadyTagged(true)
        } else {
            println('executed')
            extensions.setAlreadyTagged(false)
        }
    }
}

