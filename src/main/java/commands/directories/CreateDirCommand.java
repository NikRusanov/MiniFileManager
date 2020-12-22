package commands.directories;

import commands.Command;
import commands.CommandWithSingleArgument;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateDirCommand extends CommandWithSingleArgument implements Command {
    @Override
    public String execute(String path) {
        String result;
        try {
            super.execute(path);
            //Files.createDirectory(dirPath);
            //TODO без проверки isWritable получаем не SecurityException, а IOException
            Path parentDir = dirPath.getParent();
            if(Files.exists(parentDir)) {
                if (Files.isWritable(parentDir)) {
                    Files.createDirectory(dirPath);
                } else
                    throw new SecurityException();
            } else
                throw new IOException();
            result =  "Created. " + dirPath;
        } catch (FileAlreadyExistsException e) {
            result = "File" + path +" already exist";
        } catch (SecurityException ex) {
            result = "Permission denied";
        } catch (IOException e) {
            result = "Error create, bad path " + path;
        }
        return result;
    }
}
