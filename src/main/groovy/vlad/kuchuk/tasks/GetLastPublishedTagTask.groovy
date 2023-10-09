package vlad.kuchuk.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import vlad.kuchuk.GitBranchTaggingExtensions
import vlad.kuchuk.utils.VersionComparator

class GetLastPublishedTagTask extends DefaultTask {

    @TaskAction
    def identifyLastTag() {
        def tagsText = 'git tag -l'.execute().text
        def tagsList = tagsText.tokenize('\n')
        if (tagsList.isEmpty()) {
            tagsList.add('v0.0')
        }
        if (tagsList.size() >= 2) {
            tagsList.sort { a, b -> VersionComparator.compareVersions(a, b) }
        }
        def latestTag = tagsList.last()
        GitBranchTaggingExtensions gitExtensions = GitBranchTaggingExtensions.getInstance()
        gitExtensions.setLastTag(latestTag)
        println(latestTag)
    }
}





