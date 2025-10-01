package ch.heigvd.commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Create_class", description = "Create a new class sub-directory")
public class Create_class implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root parent;

    @Override
    public Integer call() {
        return 0;
    }
}