# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build and package the plugin
mvn clean package

# Install to local Maven repository (required before testing in another project)
mvn install

# Run tests
mvn test

# Regenerate the plugin descriptor (META-INF/maven/plugin.xml) after changing Mojo annotations
mvn plugin:descriptor
```

To test the plugin against a real Spring Boot project, install it locally first (`mvn install`), then in the target project run:
```bash
mvn io.github.waleedoff:spring-scaffold-maven-plugin:init
```

## Architecture

This is a single-Mojo Maven plugin (`packaging: maven-plugin`). The entire logic lives in one class:

**`InitMojo`** (`src/main/java/io/github/waleedoff/scaffold/InitMojo.java`) — registered as goal `init`. When executed in a consuming Maven project, it:
1. Resolves that project's `src/main/java` directory via the injected `MavenProject` parameter.
2. Walks the directory tree and takes the **first directory found** as the base package (relies on there being exactly one top-level package root).
3. Creates the following subdirectories under that base package if they don't exist:
   - `controller`, `service`, `service/interfaces`, `service/impl`, `repository`, `entity`, `security`

The plugin does **not** generate any source files — it only creates the directory scaffold. New goals should follow the same pattern: extend `AbstractMojo`, annotate with `@Mojo(name = "...")`, and inject `MavenProject` via `@Parameter(defaultValue = "${project}", readonly = true)` to access the consuming project's context.
