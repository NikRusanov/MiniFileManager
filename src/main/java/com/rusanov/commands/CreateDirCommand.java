package com.rusanov.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateDirCommand implements Command {

    @Override
    public void execute(String path) {
        try {
            Path dirPath = Paths.get(path);
            System.out.println(dirPath.getParent().toString());
           if(!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
           } else {
               System.err.println("already exist");
           }

        } catch (IOException e) {
            System.out.println("error create dir, bad path");
        }
    }
}
