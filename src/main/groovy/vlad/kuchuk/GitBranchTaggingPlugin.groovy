package vlad.kuchuk

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitBranchTaggingPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

//        project.extensions.create('gitBranchTagging', GitBranchTaggingExtensions)

        project.tasks.register('getCurrentBranchName', GetCurrentBranchNameTask) {
            setGroup('Git')
            identifyBranchName()
            finalizedBy('getLastPublishedTag')
        }
        project.tasks.register('getLastPublishedTag', GetLastPublishedTagTask) {
            setGroup('Git')
//            dependsOn('getCurrentBranchName')
            identifyLastTag()
            finalizedBy('defineBuildVersion')
        }
        project.tasks.register('defineBuildVersion', DefineBuildVersionTask) {
            setGroup('Git')
//            dependsOn('getLastPublishedTag')
            defineVersion()
        }
    }
}
