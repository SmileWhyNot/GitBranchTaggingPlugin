package vlad.kuchuk

class GitBranchTaggingExtensions {
    private static GitBranchTaggingExtensions instance = null

    String currentBranch
    String lastTag
    String buildVersion
    boolean alreadyTagged
    boolean hasUncommittedChanges

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

    void setAlreadyTagged(boolean alreadyTagged) {
        this.alreadyTagged = alreadyTagged
    }

    void setHasUncommittedChanges(boolean hasUncommittedChanges) {
        this.hasUncommittedChanges = hasUncommittedChanges
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

    boolean getAlreadyTagged() {
        return alreadyTagged
    }

    boolean getHasUncommittedChanges() {
        return hasUncommittedChanges
    }
}
