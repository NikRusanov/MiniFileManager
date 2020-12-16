package com.rusanov.commands;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileCommandsTest {
    Command command;
    @Test
    public void tryToCreateFile() {
        String pathToCreate = "/home/nik/new.txt";
        command = new FileCommands(FileCommandsList.CREATE);

        assertEquals("File created.", command.execute(pathToCreate));
    }

    @Test
    public void  tryToCreateAlreadyExistFile() {
        String pathToCreate = "/home/nik/new.txt";
        command = new FileCommands(FileCommandsList.CREATE);
        assertEquals("File \"" + pathToCreate + "\" already exist", command.execute(pathToCreate));
    }

    @Test
    public void tryToCreateFileWithBadPath() {
        String pathToCreate = "/home/nik/safljaslfha/qwyueq/new.txt";
        command = new FileCommands(FileCommandsList.CREATE);
        assertEquals("Error to CREATE", command.execute(pathToCreate));
    }

    @Test
    public void tryToRewriteFile() {
        String pathToRewrite = "/home/nik/new.txt";
        ArrayList<String> lineToWrite = new ArrayList<>();
        lineToWrite.add("line5");
        lineToWrite.add("line3");
        lineToWrite.add("line4");
        lineToWrite.add("line2");
        lineToWrite.add("line1");
        command = new FileCommands(FileCommandsList.REWRITE, lineToWrite);
        assertEquals("write " + lineToWrite.size() + " lines in " + pathToRewrite, command.execute(pathToRewrite));
    }

    ArrayList<String> lineToWrite = new ArrayList<>();
    @Test
    public void tryToRewriteUnexcitedPathFile() {
        String pathToRewrite = "/home/nik/sadasd/new.txt";

        lineToWrite.add("line5");
        lineToWrite.add("line3");
        lineToWrite.add("line4");
        lineToWrite.add("line2");
        lineToWrite.add("line1");
        command = new FileCommands(FileCommandsList.REWRITE, lineToWrite);
        assertEquals("File doesn't exist", command.execute(pathToRewrite));
    }

    @Test
    public void tryToRewriteUnexcitedFile() {
        String pathToRewrite = "/home/nik/new1.txt";
        command = new FileCommands(FileCommandsList.REWRITE, lineToWrite);
        assertEquals("File doesn't exist", command.execute(pathToRewrite));
    }

    @Test
    public void tryToAppendInFile() throws IOException {
        String pathToAppend = "/home/nik/new.txt";
        lineToWrite.clear();
        lineToWrite.add("append1");
        lineToWrite.add("append2");
        lineToWrite.add("append3");
        lineToWrite.add("append4");
        lineToWrite.add("append5");
        command = new FileCommands(FileCommandsList.APPEND, lineToWrite);
        assertEquals("write " + lineToWrite.size() + " lines in " + pathToAppend , command.execute(pathToAppend));
        String resultFile = Files.readString(Path.of(pathToAppend));
        int index = resultFile.lastIndexOf("append1\nappend2\nappend3\nappend4\nappend5");
        assertNotEquals(-1, index);
        String resultAppend = resultFile.substring(index);
        System.out.println(resultAppend);
        assertFalse(resultAppend.isEmpty());
    }

    @Test
    public void tryToAppendInUnexcitedFile() {
        String pathToAppend = "/home/nik/new1.txt";
        command = new FileCommands(FileCommandsList.APPEND, lineToWrite);
        assertEquals("File doesn't exist", command.execute(pathToAppend));
    }

    @Test
    public void tryToDeleteFile() {
        String pathToDelete = "/home/nik/new.txt";
        command = new FileCommands(FileCommandsList.REMOVE);
        assertEquals("Deleted " + pathToDelete,command.execute(pathToDelete));
    }


    @Test
    public void tryToDeleteUnexcitedFile() {
        String pathToDelete = "/home/nik/new1.txt";
        command = new FileCommands(FileCommandsList.REMOVE);
        assertEquals("File doesn't exist "  + pathToDelete,command.execute(pathToDelete));
    }

    @Test
    public void tryToDeleteFileWithoutPermissions() {
        String pathToDelete = "/etc/UPower/UPower.conf";
        command = new FileCommands(FileCommandsList.REMOVE);
        assertEquals("Permission denied",command.execute(pathToDelete));
    }
}
