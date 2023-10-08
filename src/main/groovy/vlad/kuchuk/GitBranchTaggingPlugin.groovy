package vlad.kuchuk

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitBranchTaggingPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        project.tasks.register('getCurrentBranchName', GetCurrentBranchNameTask) {
            setGroup('Git')
            identifyBranchName()
            finalizedBy('getLastPublishedTag')
        }
        project.tasks.register('getLastPublishedTag', GetLastPublishedTagTask) {
            setGroup('Git')
            identifyLastTag()
            finalizedBy('defineBuildVersion')
        }
        project.tasks.register('defineBuildVersion', DefineBuildVersionTask) {
            setGroup('Git')
            defineVersion()
            finalizedBy('assignBranchTag')
        }
        project.tasks.register('assignBranchTag', AssignBranchTagTask) {
            setGroup('Git')
            assignTag()
        }
    }
}
