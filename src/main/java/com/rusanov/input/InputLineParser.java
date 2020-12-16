package com.rusanov.input;


import com.rusanov.commands.FileCommandsList;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputLineParser {

    private FileCommandsList command;

    private List<String> separateList = new ArrayList<>();
    private String input;
    private String path;
    private String writeLines;

    public void parse(String input) throws IllegalArgumentException {
        separateList  = Arrays.asList(input.split(" "));
        this.input = input;
        setArguments();
    }

    public void setArguments() throws  IllegalArgumentException {
        switch (separateList.get(0)) {
            // ls /path/to/directory
            // mkdir /path/to/directory
            // touch /path/to/file
            // echo line >> path/to/file
            // echo line > path/to/file
            case "ls": {
                this.command = FileCommandsList.LIST;
                this.path = separateList.get(1);
            }
            break;
            case "mkdir" : {
                this.command = FileCommandsList.CREATE_DIR;
                this.path = separateList.get(1);
            }
            break;
            case  "touch" : {
                this.command = FileCommandsList.CREATE;
                this.path = separateList.get(1);
            }
            break;
            case "rm" : {
                this.command = FileCommandsList.REMOVE;
                this.path = separateList.get(1);
            }
            break;
            case "echo" : {
                parseForEcho();
            }
            break;
            default: throw new IllegalArgumentException("Error command");
        }
    }
    public String getPath() {
        return path;
    }
    public String getWriteLines() {
        return writeLines;
    }

    public FileCommandsList getCommand() {
        return command;
    }

    private void parseForEcho() throws IllegalArgumentException {
        findLine();
        findCommandType();
    }

    private void findLine() {
        int beginLine = input.indexOf("\"");
        int endLine = input.lastIndexOf("\"");
        writeLines = input.substring(beginLine + 1, endLine);
        input = input.replace(writeLines,"");
    }

    private void findCommandType() throws IllegalArgumentException{
        int separatorFirst = input.indexOf('>');
        int separatorLast = input.lastIndexOf('>');
        if(separatorFirst == separatorLast) {
            command = FileCommandsList.REWRITE;
        } else  if( (separatorLast - separatorFirst) == 1 ) {
            command = FileCommandsList.APPEND;
        } else {
            throw new IllegalArgumentException("bad string into echo command");
        }
        // обрезаем еще
        input = input.substring(separatorLast + 1);
        path = input.trim();
    }

}
