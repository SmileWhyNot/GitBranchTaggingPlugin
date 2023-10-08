package vlad.kuchuk

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GetLastPublishedTagTask extends DefaultTask{

    @TaskAction
    def identifyLastTag() {
        def tagsText = 'git tag -l --sort=version'.execute().text
        def tagsList = tagsText.tokenize('\n')
        if (tagsList.isEmpty()) {
            tagsList.add('v0.0')
        }
        def latestTag = tagsList.last()
        GitBranchTaggingExtensions gitExtensions = GitBranchTaggingExtensions.getInstance()
        gitExtensions.setLastTag(latestTag)
        println(latestTag)
    }
}
