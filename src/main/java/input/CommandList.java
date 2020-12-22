package input;

public enum CommandList {
    CREATE("touch"),
    REMOVE("rm"),
    CREATE_DIR("mkdir"),
    LIST("ls"),
    ECHO("echo"),
    ERROR("error command");

    private final String line;

    CommandList(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
