package input;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @org.junit.jupiter.api.Test
    void parseTest() throws IOException {
        InputParser parser = new InputParser();
        parser.parse("ls ~/nik/sad/sad/sassd ");
        assertEquals(CommandList.LIST, parser.getInputCommand());
        assertEquals("~/nik/sad/sad/sassd ", parser.getArguments());
    }
}