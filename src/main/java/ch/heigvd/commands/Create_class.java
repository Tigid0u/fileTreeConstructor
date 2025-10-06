package ch.heigvd.commands;

import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Create_class", description = "Create a new class sub-directory")
public class Create_class implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @CommandLine.Parameters(description = "Name of the class.")
    protected String name;

    @CommandLine.Parameters(description = "Path to create the structure")
    protected String path;


    /*@CommandLine.Option(
            names = {"-n", "--name"},
            description = "Name of the class",
            required = true)
    protected int name;*/

    @Override
    public Integer call() throws IOException {
        //Config config = getConfigFilename();
        ArrayList<String> config = new ArrayList<>(); // A sup, c'était pour pouvoir test que ça fonctionne sans Config
        config.add("labo");
        config.add("exercice");
        config.add("théorie");

        for (String e : config) { // Modifier le config pour que ça corresponde à la liste dans le config
            Path p = Paths.get(path + "/" + name + "/" + e);
            Files.createDirectories(p);
            System.out.println("Directory created: " + p);
        }

        return 0;
    }
}