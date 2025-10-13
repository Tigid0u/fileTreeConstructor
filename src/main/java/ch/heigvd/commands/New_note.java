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
 * The class will create a new note based on the path that the user wants based of configs that come
 * from the config file.
 */
public class New_note implements Callable<Integer> {
  @CommandLine.ParentCommand protected Root parent;

  @CommandLine.Parameters(description = "Path where the new note will be created")
  protected String path;

  @CommandLine.Parameters(
      description = "The title that will be displayed inside the note and as filename")
  protected String title;

  @CommandLine.Option(
      names = {"-c", "--class"},
      description = "The class name this note is for.")
  protected String class_name;

  /**
   * Create the new note in the right path with the right title
   *
   * @return
   */
  @Override
  public Integer call() {
    // Parse config file into Config object
    Config fileConfig = parent.getConfig();

    // Compose the title that will be written as the file name and title of the note.
    String title_composed =
        ((fileConfig.includeClassNameInFilename && class_name != null) ? class_name + "-" : "")
            + title
            + ((fileConfig.includeDateInFilename ? "-" + LocalDate.now() : ""));

    // Add / at the end of the path if not present
    if (path.charAt(path.length() - 1) != '/') path += "/";

    // Check if the file already exists so that we don't delete it by mistake
    File tmpDir = new File(path + title_composed + "." + fileConfig.notesFileFormat);
    if (tmpDir.exists()) {
      System.out.println("Error: File already exists at: " + tmpDir.getAbsolutePath());
      return 1;
    }

    // Ask if the user enter IN the title the extension or not
    switch (fileConfig.notesFileFormat) {
      case "md":
        // Open fileOutput
        try (Writer ofs = new FileWriter(path + title_composed + ".md", fileConfig.encoding);
            BufferedWriter bfs = new BufferedWriter(ofs)) {
          bfs.write("# " + title_composed);
        } catch (IOException e) {
          System.out.println("Exception: " + e);
          return 1;
        }
        break;
      case "txt":
        // Open fileOutput
        try (Writer ofs = new FileWriter(path + title_composed + ".txt", fileConfig.encoding);
            BufferedWriter bfs = new BufferedWriter(ofs)) {
          bfs.write(title_composed);
        } catch (IOException e) {
          System.out.println("Exception: " + e);
          return 1;
        }
        break;
      default:
        System.out.println(
            "File format not supported, supported formats are Markdown (md) and text (txt). "
                + "No note file was created, please modify your config.json file.");
        return 1;
    }
    System.out.println(
        "Note created at: " + path + title_composed + "." + fileConfig.notesFileFormat);
    return 0;
  }
}
