# CMSC 350 - OPL Text Editor

A JavaFX editor project for **CMSC 350: Organization of Programming Languages** at Commonwealth University of Pennsylvania.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/mikiahk/cmsc350-opl.git (web authentication)

or

got clone git@github.com:mikiahk/cmsc350-opl.git (ssh authentication)
```

Then open the cloned `cmsc350-opl` folder in **IntelliJ IDEA**.

> **Important:** Open the `cmsc350-opl` folder itself, not the `src` folder. The `pom.xml` file needs to be included so IntelliJ can recognize the project as a Maven project.

### 2. Allow Maven

IntelliJ **should** automatically detect the `pom.xml` file.

If IntelliJ asks you to:

- **Load Maven Project**
- **Trust Project**
- **Enable Maven integration**

Accept/enable these options.

Maven is responsible for downloading the project's dependencies.

**You do not need to manually download JavaFX.**

JavaFX is configured in `pom.xml` and Maven will download the required dependencies automatically.

Wait for IntelliJ/Maven to finish loading and indexing the project before trying to run it.

### 3. Run the Project

Once Maven has finished loading, navigate to:

```text
src/edu/commonwealthu/texteditor/Main.java
```

**Run 'Main.java'**

The JavaFX application window should open.

## Project Structure

```text
cmsc350-opl/
├── pom.xml
├── .gitignore
├── README.md
└── src/
    └── edu/
        └── commonwealthu/
            └── texteditor/
                └── Main.java
```

Additional projects should be added under:

```text
src/edu/commonwealthu/
```

For example:

```text
src/edu/commonwealthu/
├── texteditor/
│   └── Main.java
├── parser/
│   └── ...
└── ...
```

# Git Workflow


Creating a separate branch for your work can be beneficial.

First, make sure your local copy is up to date:

```bash
git checkout main       switches branch to main
git pull                downloads new changes
```

Then create a branch:

```bash
git checkout -b your-branch-name        creates and switches to branch
```

For example:

```bash
git checkout -b file-manager
```

## Make Your Changes

```bash
git status
git add .
git commit -m "Describe your changes"
```

For example:

```bash
git commit -m "Implement file manager"
```

## Push Your Branch

The first time you push a new branch:

```bash
git push -u origin your-branch-name
```

For example:

```bash
git push -u origin file-manager
```

After that:

```bash
git push
```

## Pull Request

After pushing your branch, create a **Pull Request** on GitHub.

The Pull Request should merge:

```text
your-branch-name → main
```

Have another team member review the changes before merging them into `main`.

## Catching up with main

Before starting new work:

```bash
git checkout main
git pull
```

Then create a new branch:

```bash
git checkout -b your-new-branch
```

