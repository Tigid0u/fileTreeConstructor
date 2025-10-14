---
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

# **File Tree Constructor**

Course files manager

---

# **Introduction**

**Problem**

- The problem we aim to solve
- The solution we found

![bg right](./img/documents_messydesk.jpg)

---

# **Config based approach**

Example configuration file:

```json
{
  "encoding": "UTF-8",
  "includeDateInFilename": true,
  "includeClassNameInFilename": true,
  "subdirs": ["class material", "course notes"],
  "notesFileFormat": "md"
}
```

---

# **Parse the config file**

Configuration file retrieval and parsing:

```java
  public static Config getConfigFromFile(String filename) throws IOException {
    try (Reader rd = new FileReader(filename, StandardCharsets.UTF_8);
        BufferedReader brd = new BufferedReader(rd)) {
      return mapper.readValue(brd, ch.heigvd.Config.class);
    }
  }
```

---
# **Write config to file**

Here's how the config is written back to a file:

```java
public void writeConfigToFile(String filename) throws IOException {
    try (Writer wr = new FileWriter(filename, StandardCharsets.UTF_8);
        BufferedWriter bwr = new BufferedWriter(wr)) {
      mapper.writeValue(bwr, this);
    }
  }
```

---

# **Root command**
```java
public Config getConfig() {
    Config config;
    try {
      config = Config.getConfigFromFile(configFilename);
    } catch (java.io.IOException e) {
      System.out.println(
          "Failed to open Config file : No such file\nCreating a default config file");
      config = new Config();
      try {
        config.writeConfigToFile("config.json");
      } catch (java.io.IOException e1) {
        System.out.println(e1.getMessage());
      }
    }
    return config;
  }
```


---

# **Create class**
**How to use**

```bash
java -jar fileTreeConstructor.jar Create_class <name> <path>
```
**Example**
```bash
java -jar ./demo/fileTreeConstructor.jar Create_class DAI ./demo
```

**How it works**
```java
Files.createDirectories(Path dir);
```

---

# **New course note**

---

