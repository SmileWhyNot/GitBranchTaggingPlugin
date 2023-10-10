package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions

class AssignBranchTagTask extends DefaultTask{

    @TaskAction
    def assignTag() {

        GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()
        String command = 'git tag ' + extensions.getBuildVersion()
        command.execute()
        println(command)
        def res = 'git push origin --tags'.execute().text
        println(res)
        // Check if sending tags to origin works
    }
}
