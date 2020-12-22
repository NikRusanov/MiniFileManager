package commands.files;

import input.CommandList;

import java.io.IOException;

public class EchoArgumentsParser {
    private String lineToWrite;
    private String path;
    private EchoCommandList commandList;
    private String input;
    public void parse(String input) throws IOException {
        this.input = input;
        findLine();
        findCommandType();
    }


//    private void setCommand(String command) {
//        for (var cmdEnum : EchoCommandList.values()) {
//            if(cmdEnum.getLine().equals(command)) {
//                commandList = cmdEnum;
//                break;
//            }
//        }
//    }
//
    public EchoCommandList getCommand() {
        return commandList;
    }

    private void findLine() {
        int beginLine = input.indexOf("\"");
        int endLine = input.lastIndexOf("\"");
        if(beginLine == -1 || endLine == -1
            || beginLine == endLine
          ) {
            throw new IllegalArgumentException("bad string into echo command");
        }
        lineToWrite = input.substring(beginLine + 1, endLine);
        input = input.replace(lineToWrite,"");
    }

    private void findCommandType() throws IllegalArgumentException{
        int separatorFirst = input.indexOf('>');
        int separatorLast = input.lastIndexOf('>');
        if(separatorFirst == separatorLast) {
            commandList = EchoCommandList.REWRITE;
        } else  if( (separatorLast - separatorFirst) == 1 ) {
            commandList = EchoCommandList.APPEND;
        } else {
            throw new IllegalArgumentException("bad string into echo command");
        }
        // обрезаем еще
        input = input.substring(separatorLast + 1);
        path = input.trim();
    }
    public String getLineToWrite() {
        return lineToWrite;
    }
    public String getPath() {
        return path;
    }
}
