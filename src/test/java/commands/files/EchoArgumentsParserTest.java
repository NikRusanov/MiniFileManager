package commands.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EchoArgumentsParserTest {

    @Test
    void parse() throws IOException {
        EchoArgumentsParser parser = new EchoArgumentsParser();
        parser.parse("\"string to write >> /path/to/file");
        assertEquals(EchoCommandList.APPEND,parser.getCommand());
        assertEquals("string to write",parser.getLineToWrite());
        assertEquals("/path/to/file",parser.getPath());
    }
}