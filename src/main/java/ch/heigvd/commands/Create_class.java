package ch.heigvd.commands;

import ch.heigvd.Config;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "Create_class", description = "Create a new class sub-directory")
public class Create_class implements Callable<Integer> {
  @CommandLine.ParentCommand protected Root parent;

  @CommandLine.Parameters(description = "Name of the class.")
  protected String name;

  @CommandLine.Parameters(description = "Path to create the structure")
  protected String path;

  @Override
  public Integer call() {
    Config config = parent.getConfig();

    for (String directory : config.subdirs) {
      Path p = Paths.get(path + "/" + name + "/" + directory);
      try {
        Files.createDirectories(p);
      } catch (java.io.IOException e) {
        System.out.println(e.getMessage());
      }
      System.out.println("Directory created: " + p);
    }

    return 0;
  }
}
