/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║ Parser Functionality Tool ║
    ╚═══════════════════════════╝   */
package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class JSONParser {
    public static String findValue(String json, String key) {
        HashMap<String, String> map = toHashMap(json);
        return map.get(key);
    }

    public static String findKey(String json, String value) {
        HashMap<String, String> map = toHashMap(json);
        for (String key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    public static void rename(String directory, String key, String newKey) {
        String json = FileHandler.read(directory);
        HashMap<String, String> map = toHashMap(json);
        String value = map.get(key);
        map.remove(key);
        map.put(newKey, value);
        String newJson = toJSONFromHashMap(map);
        FileHandler.write(directory, newJson);
    }

    public static void replace(String directory, String key, String newValue) {
        String json = FileHandler.read(directory);
        HashMap<String, String> map = toHashMap(json);
        map.replace(key, newValue);
        String newJson = toJSONFromHashMap(map);
        FileHandler.write(directory, newJson);
    }

    public static void sort(String directory) {
        String json = FileHandler.read(directory);
        ArrayList<String[]> asArrayList = JSONParser.toArrayList(json);
        asArrayList.sort(Comparator.comparing(o -> o[0]));
        FileHandler.write(directory, toJSONFromArrayList(asArrayList));
    }

    public static void append(String directory, String key, String value) {
        if (FileHandler.read(directory).equals("")) {
            FileHandler.write(directory, beautify("{\"" + key + "\":\"" + value + "\"}"));
        } else {
            String json = FileHandler.read(directory);
            HashMap<String, String> map = toHashMap(json);
            map.put(key, value);
            String newJson = toJSONFromHashMap(map);
            FileHandler.write(directory, newJson);
        }
    }

    public static void remove(String directory, String key) {
        String json = FileHandler.read(directory);
        HashMap<String, String> map = toHashMap(json);
        map.remove(key);
        String newJson = toJSONFromHashMap(map);
        FileHandler.write(directory, newJson);
    }

    public static ArrayList<String[]> toArrayList(String json) {
        HashMap<String, String> map = toHashMap(json);
        ArrayList<String[]> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(new String[]{key, map.get(key)});
        }
        return list;
    }

    public static HashMap<String, String> toHashMap(String json) {
        json = FileHandler.toSingleLine(json);
        json = json.replace("{", "");
        json = json.replace("}", "");
        json = json.replace("\"", "");
        String[] lines = json.split(",");
        HashMap<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] keyAndValue = line.split(":");
            map.put(keyAndValue[0], keyAndValue[1]);
        }
        return map;
    }

    public static String toJSONFromHashMap(HashMap<String, String> map) {
        StringBuilder json = new StringBuilder("{");
        for (String key : map.keySet()) {
            json.append("\"").append(key).append("\":\"").append(map.get(key)).append("\"");
            if (!key.equals(map.keySet().toArray()[map.keySet().size() - 1])) {
                json.append(",");
            }
        }
        json.append("}");
        return beautify(FileHandler.toSingleLine(json.toString()));
    }

    public static String toJSONFromArrayList(ArrayList<String[]> list) {
        StringBuilder json = new StringBuilder("{");
        for (String[] keyAndValue : list) {
            json.append("\"").append(keyAndValue[0]).append("\":\"").append(keyAndValue[1]).append("\"");
            if (!keyAndValue.equals(list.toArray()[list.size() - 1])) {
                json.append(",");
            }
        }
        json.append("}");
        return beautify(FileHandler.toSingleLine(json.toString()));
    }

    public static String beautify(String json) {
        json = FileHandler.toSingleLine(json);
        json = json.replace("{", "{\n\t");
        json = json.replace("}", "\n}");
        json = json.replace(":", ": ");
        json = json.replace(",", ",\n\t");
        return json;
    }
}