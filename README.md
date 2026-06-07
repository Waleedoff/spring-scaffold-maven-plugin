# Spring Scaffold Maven Plugin

A Maven plugin that generates a standard Spring Boot package structure inside your existing project with a single command.

## What it generates

Running the plugin creates the following directories under your base package:

```
src/main/java/com/example/yourapp/
├── controller/
├── service/
│   ├── interfaces/
│   └── impl/
├── repository/
├── entity/
└── security/
```

## Usage

### Step 1 — Add the plugin to your `pom.xml`

```xml
<plugin>
    <groupId>io.github.waleedoff</groupId>
    <artifactId>spring-scaffold-maven-plugin</artifactId>
    <version>1.0.0</version>
</plugin>
```

### Step 2 — Run the goal

```bash
mvn spring-scaffold:init
```

If Maven doesn't resolve the short prefix, use the fully qualified form:

```bash
mvn io.github.waleedoff:spring-scaffold-maven-plugin:init
```

## Requirements

- Java 17+
- Maven 3.9+
- An existing Spring Boot project with at least one package under `src/main/java`
