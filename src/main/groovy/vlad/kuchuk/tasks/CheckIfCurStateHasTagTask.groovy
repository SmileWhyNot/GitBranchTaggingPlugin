package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CheckIfCurStateHasTagTask extends DefaultTask{

    @TaskAction
    def checkIfCurStateAlreadyTagged() {

        String lastCommitHash = 'git log -1 --pretty=format:"%H"'.execute().text.trim()
        String lastTaggedCommitHash = 'git show v1.0 --pretty=format:"%H"'.execute().text.trim().split('\n')[0]
        if (lastCommitHash == lastTaggedCommitHash) {
            println('skipped')
            this.state.skipped
        } else {
            println('executed')
            this.state.executed
        }
    }
}

