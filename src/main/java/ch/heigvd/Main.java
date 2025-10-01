package ch.heigvd;

import ch.heigvd.commands.Root;
import picocli.CommandLine;

import java.io.File;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {

      String jarFilename =
              new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                      .getName();

      // Create root command
      Root root = new Root();

      int exitCode = new CommandLine(root)
                      .setCommandName(jarFilename)
                      .setCaseInsensitiveEnumValuesAllowed(true)
                      .execute(args);
      System.exit(exitCode);
  }
}
