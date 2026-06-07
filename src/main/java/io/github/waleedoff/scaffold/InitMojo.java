package io.github.waleedoff.scaffold;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name = "init")
public class InitMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {

        try {

            Path javaRoot = Paths.get(
                    project.getBasedir().getAbsolutePath(),
                    "src",
                    "main",
                    "java"
            );

            Files.walk(javaRoot)
                    .filter(Files::isDirectory)
                    .findFirst()
                    .ifPresent(basePackage -> {

                        create(basePackage.resolve("controller"));
                        create(basePackage.resolve("service"));
                        create(basePackage.resolve("service/interfaces"));
                        create(basePackage.resolve("service/impl"));
                        create(basePackage.resolve("repository"));
                        create(basePackage.resolve("entity"));
                        create(basePackage.resolve("security"));

                    });

            getLog().info("Structure created successfully");

        } catch (Exception e) {
            throw new MojoExecutionException(e);
        }
    }

    private void create(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}