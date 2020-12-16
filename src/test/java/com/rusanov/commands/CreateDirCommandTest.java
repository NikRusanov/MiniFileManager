package com.rusanov.commands;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Struct;

import static org.junit.Assert.*;

public class CreateDirCommandTest {
    Command createDirCommand = new CreateDirCommand();

    @Test
    public void createDirectoryWithoutError() throws IOException {
        String pathToCreateDir = "/tmp/tmpDir";
        Path pathDir = Path.of(pathToCreateDir);
        if(Files.exists(pathDir)) {
            Files.delete(pathDir);
        }

        String result = "Created. " + pathToCreateDir;
        assertEquals(result, createDirCommand.execute(pathToCreateDir));
    }
    @Test
    public void tryToCreateAlreadyExistDirectory()  {

        String pathToCreateDir = "/tmp/tmpDir";
        String result = "File" + pathToCreateDir +" already exist";
        assertEquals(result, createDirCommand.execute(pathToCreateDir));
    }

    @Test
    public void tryToCreateWithoutPermissions() {
        String path = "/dir";
        String result = "Permission denied";
        assertEquals(result, createDirCommand.execute(path));
    }

    @Test
    public void tryToCreateWrongPathDirectory() {
        String path = "/home/Nik/nonexistent_directory/bad_dir/bad_dir1";
        String result = "Error create, bad path " + path;
        assertEquals(result,createDirCommand.execute(path));
    }
}