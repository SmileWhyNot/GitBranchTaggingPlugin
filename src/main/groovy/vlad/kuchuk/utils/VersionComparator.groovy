package vlad.kuchuk.utils

class VersionComparator {
    static int compareVersions(String a, String b) {
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

