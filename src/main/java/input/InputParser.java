package input;

import java.io.IOException;
import java.nio.file.Path;

public class InputParser {
    private String arguments;
    private CommandList inputCommand;

    public  void parse(String input) throws IOException {
        int commandSeparator = input.indexOf(" ");
        if (commandSeparator == -1) {
            arguments = System.getProperty("user.dir");
            setCommand(input);
        } else {
            String command = input.substring(0, commandSeparator);
            arguments = input.substring(commandSeparator + 1);
            setCommand(command);
        }
    }


    public void setCommand(String command) {

        for (var cmdEnum : CommandList.values()) {
            if(cmdEnum.getLine().equals(command)) {
                inputCommand = cmdEnum;
                break;
            }
        }
    }
    public CommandList getInputCommand() {
        return  inputCommand;
    }

    public String getArguments() {
        return arguments;
    }

}
