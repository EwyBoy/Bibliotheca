# Info:
Bibliotheca is an open-source library mod build for Minecraft Forge. The library comes with a easy to use, automated block / item / tile registering system that allows you to add new content with just one simple line of code.

# Developers
To add World Stripper to your development environment you need to add the following codeblocks to your projects `build.gralde` file.

You can use https://www.cursemaven.com/ to add World Stripper to your development workspace by:
-----------------------------------
Code to add to `build.gradle`
```groovy
repositories {
    maven {
        url "https://www.cursemaven.com"
        content {
            includeGroup "curse.maven"
        }
    }
}

```
```groovy
dependencies {
    implementation fg.deobf("curse.maven:bibliotheca-268210:FileID")
}
```

FileID can be found by going to https://www.curseforge.com/minecraft/mc-mods/bibliotheca/files and click the build you are looking for.  
The ID is the numbers in the end of the build URL as seen in the image below:

![Capture](https://user-images.githubusercontent.com/5883716/118098823-b0779e00-b3d4-11eb-976d-f822658d63e4.PNG)

Builds can be found by clicking **_[here](https://www.curseforge.com/minecraft/mc-mods/bibliotheca/files)_**!
