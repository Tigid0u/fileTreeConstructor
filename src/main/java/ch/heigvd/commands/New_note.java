package ch.heigvd.commands;

import ch.heigvd.Config;
import java.io.*;
import java.time.LocalDate;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(
    name = "new_note",
    description = "Creates a new note based of the config from the config file.")
/**
 * The class will create a new note based on the path that the user wants based of configs
 * that come from the config file.
 */
public class New_note implements Callable<Integer> {
  @CommandLine.ParentCommand protected Root parent;

  @CommandLine.Option(
      names = {"-t", "--title"},
      description = "The title that will be displayed inside the note.",
      required = true)
  protected String title;

  @CommandLine.Option(
      names = {"-p", "path"},
      description = "Path where the new note will be created",
      required = true)
  protected String path;

  @CommandLine.Option(
      names = {"-c", "class"},
      description = "The class name this note is for.",
      required = true)
  protected String class_name;

    /**
     * Create the new note in the right path with the right title
     * @return
     */
  @Override
  public Integer call() {
    // Parse config file into Config object
    Config fileConfig = parent.getConfig();

    // Compose the title that will be written as the file name and title of the note.
    String title_composed =
        ((fileConfig.includeClassNameInFilename) ? class_name + "-" : "")
            + title
            + ((fileConfig.includeDateInFilename ? "-" + LocalDate.now() : ""));

    // Ask if the user enter IN the title the extension or not
    switch (fileConfig.notesFileFormat) {
      case "md":
        // Open fileOutput
        try (Writer ofs = new FileWriter(path + title_composed + ".md", fileConfig.encoding);
            BufferedWriter bfs = new BufferedWriter(ofs)) {
          bfs.write("# " + title_composed);
        } catch (IOException e) {
          System.out.println("Exception: " + e);
        }
        break;
      case "txt":
        // Open fileOutput
        try (Writer ofs = new FileWriter(path + title_composed + ".txt", fileConfig.encoding);
            BufferedWriter bfs = new BufferedWriter(ofs)) {
          bfs.write(title_composed);
        } catch (IOException e) {
          System.out.println("Exception: " + e);
        }
        break;
      default:
        System.out.println("Default value for 'notesFileFormat'");
        break;
    }
    return 0;
  }
}
