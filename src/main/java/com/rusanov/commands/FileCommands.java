package com.rusanov.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class FileCommands implements Command, CommandWithArgs {

    private final FileCommandsList command;
    private  List<String> stringToWrite;
    public FileCommands(FileCommandsList command) {
        this.command = command;
    }

    public FileCommands(FileCommandsList command, List<String> linesToWrite) {
        this.command = command;
        stringToWrite = linesToWrite;
    }

    @Override
    public String execute(String path) {
        String result  = "";
        Path pathToFile = Path.of(path);

        try {
            if(command == FileCommandsList.CREATE) {
                result = fileCreate(pathToFile);
            } else if (command == FileCommandsList.REWRITE) {
                result = fileWrite(pathToFile, StandardOpenOption.WRITE);
            } else if (command == FileCommandsList.APPEND) {

                result = fileWrite(pathToFile, StandardOpenOption.APPEND);

            } else if (command == FileCommandsList.REMOVE){
                result = deleteFile(pathToFile);

            }
        }
        catch (AccessDeniedException ex) {
            result = "Permission denied";
        }

        catch (FileAlreadyExistsException | FileNotFoundException ex) {
            result = ex.getMessage();
        } catch (IOException ex) {
            result = "Error to " + command.name();
        }


        return result;
    }

    private String fileCreate(Path pathToFile) throws FileAlreadyExistsException, IOException {
        if(!Files.exists(pathToFile)) {
            Files.createFile(pathToFile);
            return "File created.";
        } else {
            throw new FileAlreadyExistsException("File \"" + pathToFile.toString() + "\" already exist");
        }
    }

    private String fileWrite(Path pathToFile, OpenOption... options) throws  FileNotFoundException, IOException {
        if(Files.exists(pathToFile)) {
        Files.write(pathToFile, stringToWrite,  StandardCharsets.UTF_8,  options);
        } else {
            throw new FileNotFoundException("File doesn't exist");
        }
        return  "write " + stringToWrite.size() + " lines in " + pathToFile;
    }

    private String deleteFile(Path pathToFile) throws  FileNotFoundException, AccessDeniedException, IOException {
        if(Files.deleteIfExists(pathToFile)) {
            return "Deleted " + pathToFile.toString();
        } else  {
            if(!Files.exists(pathToFile)) {
                throw new FileNotFoundException("File doesn't exist " + pathToFile.toString());
            } else {
                throw new AccessDeniedException("Permission denied");
            }
        }
    }

    @Override
    public String execute(String path, List<String> linesToWrite) {
        stringToWrite = linesToWrite;
        return execute(path);
    }
}
