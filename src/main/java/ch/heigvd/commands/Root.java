package ch.heigvd.commands;

import ch.heigvd.Config;
import picocli.CommandLine;

@CommandLine.Command(
    description =
        "Small cli program that manage file structure for students, that allow to create classes and notes",
    version = "1.0.0",
    subcommands = {Create_class.class},
    scope = CommandLine.ScopeType.INHERIT,
    mixinStandardHelpOptions = true)
public class Root {

  @CommandLine.Option(
      names = {"-c", "--config"},
      description = "The name of the config file.",
      defaultValue = "config.json")
  protected String configFilename;

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
}
