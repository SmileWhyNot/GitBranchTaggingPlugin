package vlad.kuchuk

import org.gradle.api.Plugin
import org.gradle.api.Project
import vlad.kuchuk.tasks.AssignBranchTagTask
import vlad.kuchuk.tasks.CheckIfCurStateHasTagTask
import vlad.kuchuk.tasks.DefineBuildVersionTask
import vlad.kuchuk.tasks.GetCurrentBranchNameTask
import vlad.kuchuk.tasks.GetLastPublishedTagTask

class GitBranchTaggingPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        project.tasks.register('getCurrentBranchName', GetCurrentBranchNameTask) {
            setGroup('Git')
            finalizedBy('getLastPublishedTag')
        }
        project.tasks.register('getLastPublishedTag', GetLastPublishedTagTask) {
            setGroup('Git')
            finalizedBy('checkIfCurStateHasTag')
        }
        project.tasks.register('checkIfCurStateHasTag', CheckIfCurStateHasTagTask) {
            setGroup('Git')
            finalizedBy('defineBuildVersion')
        }
        project.tasks.register('defineBuildVersion', DefineBuildVersionTask) {
            setGroup('Git')
            GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()
            onlyIf {
                (!extensions.getAlreadyTagged())
            }
            finalizedBy('assignBranchTag')
        }
        project.tasks.register('assignBranchTag', AssignBranchTagTask) {
            setGroup('Git')
            GitBranchTaggingExtensions extensions = GitBranchTaggingExtensions.getInstance()
            onlyIf {
                (!extensions.getAlreadyTagged())
            }
        }
    }
}
