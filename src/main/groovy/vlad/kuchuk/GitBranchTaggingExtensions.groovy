package vlad.kuchuk

class GitBranchTaggingExtensions {
    private static GitBranchTaggingExtensions instance = null

    String currentBranchName
    String lastPublishedTag
    String buildVersion
    boolean alreadyTagged
    boolean uncommittedChanges

    private GitBranchTaggingExtensions() {}

    static GitBranchTaggingExtensions getInstance() {
        if (instance == null) {
            instance = new GitBranchTaggingExtensions()
        }
        return instance
    }

    void setCurrentBranch(String currentBranch) {
        this.currentBranchName = currentBranch
    }

    void setLastTag(String lastTag) {
        this.lastPublishedTag = lastTag
    }

    void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion
    }

    void setAlreadyTagged(boolean alreadyTagged) {
        this.alreadyTagged = alreadyTagged
    }

    void setUncommittedChanges(boolean hasUncommittedChanges) {
        this.uncommittedChanges = hasUncommittedChanges
    }

    String getCurrentBranch() {
        return currentBranchName
    }

    String getLastTag() {
        return lastPublishedTag
    }

    String getBuildVersion() {
        return buildVersion
    }

    boolean isAlreadyTagged() {
        return alreadyTagged
    }

    boolean hasUncommittedChanges() {
        return uncommittedChanges
    }
}
