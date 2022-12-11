/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║ Parser Functionality Test ║
    ╚═══════════════════════════╝   */
package test;

import main.JSONParser;
import main.FileHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.Assert.*;

public class JSONParserTest {
    @Test
    public void testFindValue() {
        String json = FileHandler.read("src/test/test.json");
        String value = JSONParser.findValue(json, "name");
        assertEquals("Liam", value);
    }

    @Test
    public void testFindKey() {
        String json = FileHandler.read("src/test/test.json");
        String key = JSONParser.findKey(json, "Liam");
        assertEquals("name", key);
    }

    @Test
    public void testRename() {
        String json = FileHandler.read("src/test/test.json");
        JSONParser.rename("src/test/test.json", "name", "firstName");
        String newJson = FileHandler.read("src/test/test.json");
        String value = JSONParser.findValue(newJson, "firstName");
        assertEquals("Liam", value);
        FileHandler.write("src/test/test.json", JSONParser.beautify(json));
    }

    @Test
    public void testReplace() {
        String json = FileHandler.read("src/test/test.json");
        JSONParser.replace("src/test/test.json", "name", "Charlie");
        String newJson = FileHandler.read("src/test/test.json");
        assertTrue(newJson.contains("Charlie"));
        FileHandler.write("src/test/test.json", JSONParser.beautify(json));
    }

    @Test
    public void testSort() {
        String json = FileHandler.read("src/test/test.json");
        JSONParser.sort("src/test/test.json");
        String newJson = FileHandler.read("src/test/test.json");
        ArrayList<String[]> asArrayList = JSONParser.toArrayList(newJson);
        asArrayList.sort(Comparator.comparing(o -> o[0]));
        assertEquals("age", asArrayList.get(0)[0]);
        assertEquals("name", asArrayList.get(1)[0]);
        FileHandler.write("src/test/test.json", JSONParser.beautify(json));
    }

    @Test
    public void testAppend() {
        String json = FileHandler.read("src/test/test.json");
        JSONParser.append("src/test/test.json", "lastName", "Glasson");
        String newJson = FileHandler.read("src/test/test.json");
        assertTrue(newJson.contains("Glasson"));
        FileHandler.write("src/test/test.json", JSONParser.beautify(json));
    }

    @Test
    public void testRemove() {
        String json = FileHandler.read("src/test/test.json");
        JSONParser.remove("src/test/test.json", "age");
        String newJson = FileHandler.read("src/test/test.json");
        assertFalse(newJson.contains("age"));
        FileHandler.write("src/test/test.json", JSONParser.beautify(json));
    }

    @Test
    public void testToArrayList() {
        String json = FileHandler.read("src/test/test.json");
        ArrayList<String[]> list = JSONParser.toArrayList(json);
        assertEquals("name", list.get(0)[0]);
        assertEquals("Liam", list.get(0)[1]);
        assertEquals("age", list.get(1)[0]);
        assertEquals("19", list.get(1)[1]);
    }

    @Test
    public void testToHashMap() {
        String json = FileHandler.read("src/test/test.json");
        HashMap<String, String> map = JSONParser.toHashMap(json);
        assertEquals("Liam", map.get("name"));
        assertEquals("19", map.get("age"));
    }

    @Test
    public void testToSingleLine() {
        String json = FileHandler.read("src/test/test.json");
        String singleLine = FileHandler.toSingleLine(json);
        assertTrue(singleLine.contains("name"));
        assertTrue(singleLine.contains("Liam"));
        assertTrue(singleLine.contains("age"));
        assertTrue(singleLine.contains("19"));
    }

    @Test
    public void testBeautify() {
        String json = FileHandler.read("src/test/test.json");
        String beautified = JSONParser.beautify(json);
        assertTrue(beautified.contains("name"));
        assertTrue(beautified.contains("Liam"));
        assertTrue(beautified.contains("age"));
        assertTrue(beautified.contains("19"));
    }
}