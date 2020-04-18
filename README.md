Forked from: https://github.com/electronstudio/python-minecraft-plugin

At the moment this is a proof of concept to pass values from Java to Python with use of GraalVM.

## Current Work

Added function to `SamplePlugin.java` to call Python function, passing a string.

## Setup

1. Download and unzip GraalVM: https://github.com/graalvm/graalvm-ce-builds/releases

2. Install Python for GraalVM:

    `~/Downloads/graalvm-ce-java8-20.0.0/Contents/Home/bin/gu install python`

3. Download PaperMC minecraft server (fork of Spigot): https://papermc.io/downloads

4. Check that you can run PaperMC using GraalVM

    `~/Downloads/graalvm-ce-java8-20.0.0/Contents/Home/bin/java -jar paper-*.jar`

5. Compile the sample plugin:

    `./gradlew jar`

6. Copy the generated plugin jar into the plugins folder of PaperMC and re-run paperMC.

    `cp build/libs/*.jar ~/papermc/plugins/`