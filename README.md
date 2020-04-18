Forked from: https://github.com/electronstudio/python-minecraft-plugin

At the moment this is a proof of concept to pass values from Java to Python with use of GraalVM.

## Current Work

Added function to `SamplePlugin.java` to call Python function, passing a string.

## Setup

1. Install GraalVM: https://www.graalvm.org/docs/getting-started/linux

2. Install Python for GraalVM: https://www.graalvm.org/docs/getting-started/#running-python
    - `gu install python`

3. Download [PaperMC](https://papermc.io/) minecraft server (fork of [Spigot](https://www.spigotmc.org)): https://papermc.io/downloads

4. Check that you can run PaperMC using GraalVM

    `~java -jar paper-*.jar`

5. Compile the sample plugin:

    `./gradlew jar`

6. Copy the generated plugin jar into the plugins folder of PaperMC.

    `cp build/libs/*.jar ~/papermc/plugins/`

7. Run PaperMC