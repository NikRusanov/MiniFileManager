package commands;

import java.nio.file.Path;

public class CommandWithSingleArgument implements Command{
    protected Path dirPath;
    @Override
    public String execute(String inputString) {
        if(inputString.isEmpty()) {
            inputString = System.getProperty("user.dir");
        }
        setDirPath(inputString);
        return inputString;
    }

    public void setDirPath(String strPath) {
        dirPath = Path.of(strPath);
    }
}
