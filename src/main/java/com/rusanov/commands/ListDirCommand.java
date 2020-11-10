package com.rusanov.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ListDirCommand implements Command{

    @Override
    public void execute(String path) {
        try(Stream<Path> walk = Files.list(Paths.get(path))) {
            walk.map(Path::getFileName).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("directory doesnt exist");
        }
    }
}
