package commands;

import commands.directories.CreateDirCommand;
import commands.directories.ListCommand;
import commands.files.EchoCommand;
import commands.files.RemoveCommand;
import commands.files.TouchCommand;
import input.CommandList;
import input.InputParser;

import java.io.IOException;
import java.util.HashMap;

public class CommandExecutor implements Command {

    HashMap<CommandList,Command> commands = new HashMap<>();
    Command command;

    public CommandExecutor() {
        commands.put(CommandList.LIST,  new ListCommand());
        commands.put(CommandList.ECHO,  new EchoCommand());
        commands.put(CommandList.CREATE,   new TouchCommand());
        commands.put(CommandList.REMOVE,   new RemoveCommand());
        commands.put(CommandList.CREATE_DIR,   new CreateDirCommand());
    }
    @Override
    public String execute(String inputString) {
        String result;
        InputParser inputParser = new InputParser();

        try {
            inputParser.parse(inputString);

            String arguments  = inputParser.getArguments();
            CommandList currentInputCommand = inputParser.getInputCommand();
            command = getCurrentCommand(currentInputCommand);

            result = command.execute(arguments);
        } catch (IOException exception) {
            result =  exception.getMessage();
        }

        return result;
    }

    private Command getCurrentCommand(CommandList currentCommand) {
        return commands.getOrDefault(currentCommand,new ErrorCommand());
    }
}
