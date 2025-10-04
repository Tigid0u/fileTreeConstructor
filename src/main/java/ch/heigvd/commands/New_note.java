package ch.heigvd.commands;

import java.io.*;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@CommandLine.Command(
    name = "new_note",
    description = "Creates a new note based of the config from the config file.")
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

  // @Override
  public Integer call() {
    System.out.println("Title: " + title + " Path: " + path);

    // Parse config file into Config object
      //TODO Config fileConf = parseConfig();

      String title;

      // Compose the title name
      if (fileConfig.date) {
          LocalDate date = LocalDate.now();
          title += "-" + date;
      }
      if (fileConfig.course) {
          //TODO: how to know in which course I am in
          title += "-" + course;
      }

      // Ask if the user enter IN the title the extension or not
      switch(fileConfig.format){
          case .MD:
              // Open fileOutput
              try(Writer ofs = new FileWriter(path + title + ".md", fileConfig.charset); BufferedWriter bfs = new BufferedWriter(ofs)){
                  bfs.write("# ");
                  bfs.write(title);
              } catch (IOException e) {
                  System.out.println("Exception: " + e);
              }
              break;
          default:
              System.out.println("Default value for format");
              break;
      }

    // Close the file
    bfs.flush();
      bfs.close();
      ofs.close();
    return 0;
  }
}
