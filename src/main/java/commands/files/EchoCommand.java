package commands.files;

import commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class EchoCommand implements Command {

    private String lineToWrite;
    private String path;
    @Override
    public String execute(String inputString) {
       String result;
        EchoArgumentsParser parser = new EchoArgumentsParser();
        try {
            parser.parse(inputString);
            path = parser.getPath();
            lineToWrite = parser.getLineToWrite();
            switch (parser.getCommand()) {
                case APPEND -> result = fileWrite(StandardOpenOption.APPEND);
                case REWRITE -> result = fileWrite(StandardOpenOption.WRITE);
                default -> throw new IOException("Unknown command separator");
            }
        } catch (IOException exception) {
            result = exception.getMessage();
        }
        return result;
    }

    private String fileWrite(OpenOption... options) throws IOException {
        Path filePath = Path.of(path);
        if(Files.isDirectory(filePath)) { throw new IOException("its directory");}
        if(Files.exists(filePath)) {
            Files.write(filePath, Collections.singleton(lineToWrite),  StandardCharsets.UTF_8,  options);
        } else {
            throw new FileNotFoundException("File doesn't exist");
        }
        return  "write " + lineToWrite.length() + " lines in " + path;
    }

}
