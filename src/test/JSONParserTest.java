/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║ Parser Functionality Test ║
    ╚═══════════════════════════╝   */
package test;

import main.JSONParser;
import main.io.FileHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JSONParserTest {
    @Test
    public void getTempDirectory() {
        assertTrue(FileHandler.getTempDirectory().endsWith("\\AppData\\Local\\Temp\\"));
    }

    @Test
    public void create() {
        FileHandler.create(FileHandler.getTempDirectory() + "ParserTest.json");
    }

    @Test
    public void write() {
        FileHandler.write(FileHandler.getTempDirectory() + "ParserTest.json", "{\n\t\"a\":\"x\"\n}");
    }

    @Test
    public void toSingleLine() {
        assertEquals("{\t\"a\":\"x\"}", JSONParser.toSingleLine(FileHandler.getTempDirectory() + "ParserTest.json"));
    }

    @Test
    public void toMultipleLines() {
        assertEquals("{\n\t\"a\":\"x\"\n}", FileHandler.toMultipleLines(FileHandler.getTempDirectory() + "ParserTest.json"));
    }

    @Test
    public void beautifier() {
        assertEquals("{\n\t\"a\":\"x\"\n}", JSONParser.beautifier(FileHandler.getTempDirectory() + "ParserTest.json"));
    }
}