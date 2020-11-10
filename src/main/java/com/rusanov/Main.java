package com.rusanov;

import com.rusanov.commands.Command;
import com.rusanov.commands.CreateDirCommand;
import com.rusanov.commands.ListDirCommand;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        Command list = new ListDirCommand();
        list.execute(currentDir);
       String pathToCreateDir = "C:\\tmp";
        Command mkdir = new CreateDirCommand();
        mkdir.execute(pathToCreateDir);
    }
}
