package commands.files;

import commands.Command;
import commands.CommandWithSingleArgument;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;

public class RemoveCommand extends CommandWithSingleArgument implements Command {
    @Override
    public String execute(String inputString) {
        String result;
        //Общий Родитель для всех комманд с 1 аргументом?
        super.execute(inputString);
        try {
            if(Files.deleteIfExists(dirPath)) {
                result =  "Deleted " + dirPath.toString();
            } else  {
                if(!Files.exists(dirPath)) {
                    throw new FileNotFoundException("File doesn't exist " + dirPath.toString());
                } else {
                    throw new AccessDeniedException("Permission denied");
                }
            }
        } catch (IOException exception) {
            result = "File not found" ;
        }
        return result;
    }
}
