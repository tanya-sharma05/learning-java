# Maven

> A free, open-source build automation tool developed by the **Apache Software Foundation**, primarily used for Java projects.

---

## What is Maven?

- Java build and project management tool
- Manages dependencies and automates the build process
- Automates: compiling code, managing dependencies, packaging, testing, and deployment

---

## What Maven Can Do

| Task | Description |
|------|-------------|
| 📁 Project Structure | Creates the standard folder layout automatically |
| 📦 Dependency Management | Downloads and manages required libraries automatically |
| 🧪 Testing | Executes unit tests using frameworks like JUnit |
| ⚙️ Compile | Compiles the project's source code |
| 🚀 Package | Packages the project into JAR or WAR format |

---

## Maven Terminologies

### Archetype
A template that defines the structure of a Maven project.

| Archetype | Use |
|-----------|-----|
| `maven-archetype-quickstart` | Java stand-alone application |
| `maven-archetype-webapp` | Java web application |

### Group ID
Represents the organization or company name. Follows **reverse domain name** convention.

```
Examples: com.tcs, com.ibm, com.telusko
```

### Artifact ID
Represents the name of the project/module.

```
Examples: telusko-app, amazon-app, flipkart-app
```

### Version
Specifies the version of the project.

| Version | Meaning |
|---------|---------|
| `0.0.1-SNAPSHOT` | Under development |
| `1.0-RELEASE` | Final version, delivered to client |

### Packaging Type
Defines the packaging format of the project.

```
Examples: jar, war
Note: Default packaging is jar if not specified.
```

---

## Creating a Maven Project

```bash
mvn archetype:generate 
-DgroupId=com.org 
-DartifactId=org-app 
-DarchetypeArtifactId=maven-archetype-quickstart 
-DarchetypeVersion=1.0 
-DinteractiveMode=false
```

---

## pom.xml Structure

The **Project Object Model** — the heart of every Maven project.

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.org</groupId>
  <artifactId>org-app</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

---

## Maven Dependencies

External libraries or modules required for project development.

- Find dependencies at: [https://mvnrepository.com](https://mvnrepository.com)
- Common examples: `spring`, `hibernate`, `junit`, `kafka`, `redis`

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-core</artifactId>
  <version>6.1.7</version>
</dependency>
```

---

## Maven Goals

Used to perform specific steps in the Maven build lifecycle.

```bash
$ mvn <goal>
```

| Goal | What it Does |
|------|-------------|
| `clean` | Deletes the `target/` directory (removes compiled files) |
| `compile` | Compiles `.java` files → `.class` files into `target/` |
| `test` | Runs unit tests (`= compile + test`) |
| `package` | Packages into JAR/WAR (`= compile + test + package`) |
| `install` | Installs the package into the local repository |
| `deploy` | Deploys to a remote repository |

```bash
mvn clean package # Clean build + package into jar/war
```

---

## Maven Build Lifecycle

Three built-in lifecycles:

1. **default** → Main lifecycle for building your application *(mostly used)*
2. **clean** → Cleans the `target/` directory
3. **site** → Generates project documentation

### Default Lifecycle Phases (in order):

```
validate → compile → test → package → verify → install → deploy
```

---

## Maven Repositories

Storage locations for Maven dependencies (artifacts/libraries).

| Repository | Description |
|------------|-------------|
| **Local** | `.m2/repository` folder on your machine. Maven checks here first. |
| **Central** | Official online repo at `https://repo.maven.apache.org/maven2`. Contains millions of open-source libraries. |
| **Remote** | Company or third-party server. Defined in `pom.xml` or `settings.xml`. Used for internal/private jars. |

### Dependency Resolution Order:
**Local Repo → Central Repo → Remote Repo**
