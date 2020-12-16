package com.rusanov.commands;

import org.junit.Test;

import static org.junit.Assert.*;


public class ListDirCommandTest {

    Command listDirectoriesCommand = new ListDirCommand();

    @Test
    public void listDirTest() {
        String currentDir = System.getProperty("user.dir");
        String resultDirectories = "pom.xml\nFileManager.iml\nsrc\n.gitignore\n.idea\n.git\ntarget";
        assertEquals(resultDirectories, listDirectoriesCommand.execute(currentDir));
    }
    @Test
    public void tryToListUnexcitingDirectory() {
        String directory = "/lsadsdk";
        String result = "directory doesnt exist";
        assertEquals(result, listDirectoriesCommand.execute(directory));
    }
    @Test
    public void listEmptyDirectory() {
        String directory  = "/home/nik/Documents/";
        String result = "Empty directory" + directory;
        assertEquals(result, listDirectoriesCommand.execute(directory));
    }
}