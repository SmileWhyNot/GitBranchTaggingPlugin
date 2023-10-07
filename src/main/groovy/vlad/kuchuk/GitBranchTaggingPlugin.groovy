package vlad.kuchuk

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitBranchTaggingPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.group = 'Git'
        project.tasks.register('getCurrentBranch') {
            doLast {
                println 'git symbolic-ref --short HEAD'.execute().text
            }
        }
    }
}
