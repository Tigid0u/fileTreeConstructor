package ch.heigvd.commands;

import picocli.CommandLine;
import ch.heigvd.Config;

@CommandLine.Command(
    description = "Small cli program that manage file structure for students, that allow to create classes and notes",
    version = "1.0.0",
    subcommands = {
    },
    scope = CommandLine.ScopeType.INHERIT,
    mixinStandardHelpOptions = true)
public class Root {

  @CommandLine.Option(
      names = {"-c", "--config"},
      description = "The name of the config file.",
      defaultValue = "config.json")
  protected String configFilename;

  public Config getConfigFilename() {
    return Config.getConfigFromFile(configFilename);
  }
}
