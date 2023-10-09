package vlad.kuchuk

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class GetLastPublishedTagTask extends DefaultTask{

    @TaskAction
    def identifyLastTag() {
        def tagsText = 'git tag -l'.execute().text
        def tagsList = tagsText.tokenize('\n')
        if (tagsList.isEmpty()) {
            tagsList.add('v0.0')
        }
        if (tagsList.size() >= 2) {
            tagsList.sort(versionComparator)
        }
        def latestTag = tagsList.last()
        GitBranchTaggingExtensions gitExtensions = GitBranchTaggingExtensions.getInstance()
        gitExtensions.setLastTag(latestTag)
        println(latestTag)
    }

    @Input
    def versionComparator = { String a, String b ->
        def versionA = a.replaceAll(/^v/, '').tokenize('-')[0].tokenize('.').collect { it.toInteger() }
        def versionB = b.replaceAll(/^v/, '').tokenize('-')[0].tokenize('.').collect { it.toInteger() }

        int result = 0
        for (int i = 0; i < Math.min(versionA.size(), versionB.size()) && result == 0; i++) {
            result = versionA[i] <=> versionB[i]
        }

        if (result == 0) {
            result = versionA.size() <=> versionB.size()
        }

        return result
    }
}
