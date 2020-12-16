package com.rusanov.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListDirCommand implements Command{

    @Override
    public String execute(String path) {
        return  getListDirectories(path);
    }

    private String getListDirectories(String path) {
        String dirList;
        try(Stream<Path> walk = Files.list(Paths.get(path))) {
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
