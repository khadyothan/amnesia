# AMNESIA

# Setting up JavaFX in VS Code

Follow these steps to set up JavaFX in Visual Studio Code:

1. **Install JavaFX**
Download and install the latest version of JavaFX. After installation, set up the path variables. For example:
``` PATH_TO_FX="path/to/javafxsdk/lib" ```

2. **Add Jar files to Java Project**
In the Java project section of VS Code, add the necessary Jar files to the referenced packages.

3. **Configure Settings**
Add the paths to settings.json and include the following key-value pair in launch.json:
``` "vmArgs": "--module-path 'path to javafxsdk lib' --add-modules javafx.controls,javafx.fxml" ```

4. **Compile and Run**
Execute the following commands to compile and run your Java class with JavaFX code:
``` $ javac --module-path "path/to/javafxsdk/lib" --add-modules javafx.controls YourJavaFile.java ```
``` $ java --module-path "path/to/javafxsdk/lib" --add-modules javafx.controls YourJavaFile ```


