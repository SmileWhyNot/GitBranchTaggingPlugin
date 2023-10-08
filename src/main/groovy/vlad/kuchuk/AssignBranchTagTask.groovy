package vlad.kuchuk

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class AssignBranchTagTask extends DefaultTask{

    @TaskAction
    def assignTag() {

        GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()
        String command = 'git tag ' + extensions.getBuildVersion()
        command.execute()
        println(command)
    }
}
