package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions

class CheckIfUncommittedChangesTask extends DefaultTask {

    @TaskAction
    void checkIfUncommittedChanges() {
        File logFile = new File("uncommitted_changes.log")

        try {
            FileWriter writer = new FileWriter(logFile, true)
            def result = 'git status --untracked-files=no'.execute().text.trim()
            GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()

            if (!result.contains('nothing to commit')) {
                writer.write('Uncommitted changes found ' + extensions.lastTag + '.uncommitted\n')
                extensions.setHasUncommittedChanges(true)
            } else {
                extensions.setHasUncommittedChanges(false)
            }

            writer.close()
        } catch (IOException e) {
            throw new RuntimeException("Error writing to log file", e)
        }
    }
}
