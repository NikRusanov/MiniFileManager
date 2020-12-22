package commands;

public class ErrorCommand implements Command{

    @Override
    public String execute(String inputString) {
        return "Error command";
    }
}
