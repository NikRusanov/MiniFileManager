package com.rusanov.input;

import com.rusanov.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App {
    private final HashMap<FileCommandsList, Command> commands = new HashMap<>();
    private final HashMap<FileCommandsList, CommandWithArgs> commandsWithArgs = new HashMap<>();
    private final InputLineParser inputParser = new InputLineParser();

    public App() {
        commands.put(FileCommandsList.CREATE, new FileCommands(FileCommandsList.CREATE));
        commands.put(FileCommandsList.REMOVE, new FileCommands(FileCommandsList.REMOVE));
        commands.put(FileCommandsList.CREATE_DIR, new CreateDirCommand());
        commands.put(FileCommandsList.LIST, new ListDirCommand());


        commandsWithArgs.put(FileCommandsList.REWRITE, new FileCommands(FileCommandsList.REWRITE));
        commandsWithArgs.put(FileCommandsList.APPEND, new FileCommands(FileCommandsList.APPEND));

    }
    public void start() throws IOException {
       try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
           System.out.println("write command");
           String inputLine = reader.readLine();

        while (!inputLine.equals("exit")) {

            inputParser.parse(inputLine);
            Command currentCommand = commands.get(inputParser.getCommand());
            if(currentCommand != null) {
                System.out.println(currentCommand.execute(inputParser.getPath()));
            } else  {
                CommandWithArgs currentCommandWithArgs = commandsWithArgs.get(inputParser.getCommand());
                List<String> lines  = Arrays.asList(inputParser.getWriteLines().split("\n"));
                currentCommandWithArgs.execute(inputParser.getPath(), lines);
            }
            System.out.println("write command");
            inputLine = reader.readLine();
        }
       } catch (IOException exception) {
           System.err.println("Error with read input.\nTrace:" + exception.getMessage());
       }
    }
}
