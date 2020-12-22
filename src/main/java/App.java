import commands.CommandExecutor;
import input.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class App {

    public void start() {
        CommandExecutor executor = new CommandExecutor();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("write command");
            String inputLine = reader.readLine();

            while (!inputLine.equals("exit")) {
                System.out.println(executor.execute(inputLine));
                System.out.println("write command");
                inputLine = reader.readLine();
            }
        } catch (IOException exception) {
            System.err.println("Error with read input.\nTrace: " + exception.getMessage());
        }
    }
}
