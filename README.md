# 📁🌲🛠️ File Tree Constructor

## Maintainers

This project is maintained by:

- Alberto De Sousa Lopes [@Alb-E](https://github.com/Alb-E)
- Maikol Correia Da Silva [@Maikol-Da-Silva](https://github.com/Maikol-Da-Silva)
- Nolan Evard [@Tigid0u](https://github.com/Tigid0u)

## Description of the project

Managing course files as a student can quickly become repetitive and disorganized. Each new class requires setting up folders, creating note files, and maintaining consistent naming — a tedious and error-prone process.

This project aims to automate and simplify course organization through a lightweight CLI tool. The program uses a configurable setup to generate directory structures and note files for your classes.

It actually features 2 main sub-commands:

- `create_class`: Creates a new class directory structure based on your config file.

- `new_note`: Generates a new course note file with the proper format, encoding, and title.

## Usage examples

Here are a few usage examples

**Get help:**

```bash
java -jar target/fileTreeConstructor-1.0-SNAPSHOT.jar -h
```

Output:

```
Usage: fileTreeConstructor-1.0-SNAPSHOT.jar [-hV] [-c=<configFilename>]
       [COMMAND]
Small cli program that manage file structure for students, that allow to create
classes and notes
  -c, --config=<configFilename>
                  The name of the config file. 
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  Create_class  Create a new class sub-directory
  new_note      Creates a new note based of the config from the config file.
```

You can get the same help page for the subcommands by using the `-h` option on a subcommand like so:

```bash
java -jar target/fileTreeConstructor-1.0-SNAPSHOT.jar <subcommand> -h
```

**Create a new class**

Here we will create the `DAI` class in the home directory

```bash
java -jar target/fileTreeConstructor-1.0-SNAPSHOT.jar Create_class DAI ~/
```

Output:

```
Failed to open Config file : No such file
Creating a default config file
Directory created: ~/DAI/class material
Directory created: ~/DAI/course notes
``` 

Let's quickly analyze the logs:

- `Failed to open Config file : No such file`: Means no config file was specified or found. If no config file is specified the program looks for a `config.json` file in the current working directory.
- `Creating a default config file`: As no file was found the program creates a default config file
- `Directory created: ~/DAI/class material`, `Directory created: ~/DAI/course notes`: the created directories. These are the default directories, you can edit those in the `config.json` file.

**Create a new course note**

Let's create our very first course note as a Markdown file (default).

```bash
java -jar target/fileTreeConstructor-1.0-SNAPSHOT.jar new_note -c DAI ~/DAI/course\ notes my-first-note
```

 Output:

```
Note created at: ~/DAI/course notes/DAI-my-first-note-2025-10-13.md
```

## Configuration

The program relies on a configuration file (in JSON format) to determine how to create class folders and note files.
It allows you to customize file naming, encoding, directory structure, and note format.

If no configuration file is found, a default one is automatically generated:

```json
{
  "encoding": "UTF-8",
  "includeDateInFilename": true,
  "includeClassNameInFilename": true,
  "subdirs": ["class material", "course notes"],
  "notesFileFormat": "md"
}
```

**Configuration options:**

| Key                            | Type                | Description                                                                                                      |
| ------------------------------ | ------------------- |------------------------------------------------------------------------------------------------------------------|
| **encoding**                   | `string`            | The character encoding used for new note files. Default is `"UTF-8"`.                                            |
| **includeDateInFilename**      | `boolean`           | Whether to include the current date in the generated note filename                                               |
| **includeClassNameInFilename** | `boolean`           | Whether to include the class name in the filename.                                                               |
| **subdirs**                    | `array` of `string` | The list of subdirectories to create when initializing a new class. You can customize or add new ones as needed. |
| **notesFileFormat**            | `string`            | The file format/extension of the notes. Supported formats are: `"md"`, `"txt"`.                                  |


## How to get started

1. **Clone the project**
    ```bash
    git clone git@github.com:Tigid0u/fileTreeConstructor.git
    ```
2. **Build/Package app as a JAR file using the included Maven wrapper**
    ```bash
   ./mvnw dependency:go-offline clean spotless:apply compile package
   ```
3. **Run the program using one of the command listed above in the 'Usage Example' section**

## Contribute

If you wish to help this project evolve, create an issue as follows to be added as a contributor:

- **Title:** [\<your username>] Request to contribute
- **Description:** Briefly state your motivations

Tag the maintainers in a comment to maximize the chances for your request to be reviewed in the shortest delays.