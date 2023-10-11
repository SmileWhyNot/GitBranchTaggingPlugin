# Gradle Git Branch Tagging Plugin

The Gradle Git Branch Tagging Plugin is a helpful tool that simplifies the process of versioning your project based on the Git branch you are working on. This plugin automatically determines the appropriate version for your project, creates Git tags, and publishes them to remote repository.

## Getting Started

Follow these steps to use the Gradle Git Branch Tagging Plugin in your project:

### 1. Clone the Repository

```shell
git clone <repository-url>
```

### 2. After cloning
- Update gradle project and call build task
- If you run into exceptions with groovy version, you can change it. Then clean and rebuild project;
- In Gradle tasks click publishing -> publishToMavenLocal

#### After this you can find your plugin in .m2 folder

### 2. Project Setup

In the project where you plan to use the plugin, make the following changes:

#### In `settings.gradle`

Add the following block to `settings.gradle` to configure the plugin repositories:

```groovy
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
```

#### In `build.gradle`

Add the plugin to your `build.gradle` file by specifying its ID and version:

```groovy
plugins {
    id 'vlad.kuchuk.gitbranchtagging' version '0.1'
}
```

### 3. Update Gradle Projects

After adding the plugin to your `build.gradle` file, update your Gradle projects to fetch the plugin and its dependencies.

### 4. Running the Plugin

Now that the plugin is set up, you can execute the `tagging` task, which will automatically tag your project based on the Git branch.

```shell
./gradlew tagging
```

### 5. Check the Git Tags

After running the `tagging` task, the plugin will create and publish Git tags according to the branch's rules. You can find these tags in your remote/local repository.

## Plugin Behavior

The Gradle Git Branch Tagging Plugin works as follows:

- It determines the last published version (the latest Git tag).
- It calculates the version of the current build based on the branch, following these rules:
  - `dev/qa`: Increment the minor version.
  - `stage`: Add `-rc` as a postfix.
  - `master/main`: Increment the major version.
  - Any other branch: Add `-SNAPSHOT` as a postfix.
- It assigns the appropriate Git tag to the project.
- It publishes the tag to the Git repository.

### Notes

- If a Git tag has already been assigned to the current state of the project, a new one will not be created.
- If there are uncommitted changes in the working directory, the plugin will log the build number with the `.uncommitted` postfix, but it will not create a Git tag.

### Plugin assumes that you project is already set up with git and github remote

Enjoy using the Gradle Git Branch Tagging Plugin to automate your project's versioning and Git tagging!
