package com.rusanov.commands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateDirCommand implements Command {

    @Override
    public String execute(String path) {
        String result;
        try {
            Path dirPath = Paths.get(path);
            //Files.createDirectory(dirPath);
            //TODO без проверки isWritable получаем не SecurityException, а IOException
            Path parentDir =dirPath.getParent();
            if(Files.exists(parentDir)) {
                if (Files.isWritable(parentDir)) {
                    Files.createDirectory(dirPath);
                } else
                    throw new SecurityException();
            } else
                throw new IOException();
            result =  "Created. " + dirPath;
        } catch (FileAlreadyExistsException e) {
            result = "File" + path +" already exist";
        } catch (SecurityException ex) {
            result = "Permission denied";
        } catch (IOException e) {
            result = "Error create, bad path " + path;
        }
        return result;
    }

}
