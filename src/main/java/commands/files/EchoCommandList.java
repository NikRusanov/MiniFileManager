package commands.files;

public enum EchoCommandList {
    APPEND(">>"),
    REWRITE(">");


    private final String line;

    EchoCommandList(String line) {
        this.line = line;
    }
    public String getLine() {
        return line;
    }
}

