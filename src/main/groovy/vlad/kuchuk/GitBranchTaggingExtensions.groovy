package vlad.kuchuk

class GitBranchTaggingExtensions {
    private static GitBranchTaggingExtensions instance = null

    String currentBranch
    String lastTag
    String buildVersion

    private GitBranchTaggingExtensions() {}

    static GitBranchTaggingExtensions getInstance() {
        if (instance == null) {
            instance = new GitBranchTaggingExtensions()
        }
        return instance
    }

    void setCurrentBranch(String currentBranch) {
        this.currentBranch = currentBranch
    }

    void setLastTag(String lastTag) {
        this.lastTag = lastTag
    }

    void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion
    }

    String getCurrentBranch() {
        return currentBranch
    }

    String getLastTag() {
        return lastTag
    }

    String getBuildVersion() {
        return buildVersion
    }
}
