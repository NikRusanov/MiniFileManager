package com.rusanov.input;

import com.rusanov.commands.FileCommandsList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineParserTest {


    String input;
    InputLineParser parser = new InputLineParser();
    @Test
    public void parsingLsInputTest() {
        input = "ls /path/to/directory";
        parser.parse(input);
        assertEquals(FileCommandsList.LIST, parser.getCommand());
        assertEquals("/path/to/directory", parser.getPath());
    }

    @Test
    public void parsingCreateDirectoryInputTest() {
        input = "mkdir /path/to/directory";
        parser.parse(input);
        assertEquals(FileCommandsList.CREATE_DIR, parser.getCommand());
        assertEquals("/path/to/directory",parser.getPath());
    }

    @Test
    public void parsingCreateFileInputTest() {
        input = "touch /path/to/directory";
        parser.parse(input);
        assertEquals(FileCommandsList.CREATE, parser.getCommand());
        assertEquals("/path/to/directory",parser.getPath());
    }


    @Test
    public void parsingRewriteFileInputTest() {
        input = "echo \"line to rewrite\" > /path/to/file";
        parser.parse(input);
        assertEquals(FileCommandsList.REWRITE, parser.getCommand());
        assertEquals("/path/to/file",parser.getPath());
        assertEquals("line to rewrite",parser.getWriteLines());
    }

    @Test
    public void parsingAppendFileInputTest() {
        input = "echo \"line to rewrite\" >> /path/to/file";
        parser.parse(input);
        assertEquals(FileCommandsList.APPEND, parser.getCommand());
        assertEquals("/path/to/file",parser.getPath());
        assertEquals("line to rewrite",parser.getWriteLines());
    }

    @Test
    public  void parsingWrongEchoCommand() {
        input = "echo \"   line to rewrite\"   >> /path/to/file";
        try {
            parser.parse(input);
        } catch (IllegalArgumentException ex) {
            assertEquals("bad string into echo command", ex.getMessage());
        }

    }
}
