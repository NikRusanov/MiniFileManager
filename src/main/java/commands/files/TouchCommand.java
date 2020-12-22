package commands.files;

import commands.Command;
import commands.CommandWithSingleArgument;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class TouchCommand extends CommandWithSingleArgument implements Command {
    @Override
    public String execute(String inputString) {
        super.execute(inputString);
        String result;
        try {


            if(!Files.exists(dirPath)) {
                Files.createFile(dirPath);
                result =  "File created.";
            } else {
                throw new FileAlreadyExistsException("File \"" + dirPath.toString() + "\" already exist");
            }
        } catch (IOException ex) {
            result = ex.getMessage();
        }
        return result;
    }
}
