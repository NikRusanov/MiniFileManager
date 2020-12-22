package commands.directories;

import commands.Command;
import commands.CommandWithSingleArgument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListCommand extends CommandWithSingleArgument implements Command {
    @Override
    public String execute(String path) {
        super.execute(path);
        String dirList;
        try(Stream<Path> walk = Files.list(dirPath)) {
            dirList = walk.map((p) -> p.getFileName().toString())
                    .collect(Collectors.joining("\n"));

        } catch (SecurityException ex ) {
            dirList = "access denied";
        } catch (IOException e) {
            dirList = "directory doesnt exist";
        }
        return dirList.isEmpty() ? "Empty directory" + path : dirList;
    }
}
