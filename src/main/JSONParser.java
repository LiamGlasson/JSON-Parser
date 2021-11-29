/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║ Parser Functionality Tool ║
    ╚═══════════════════════════╝   */
package main;

import main.io.FileHandler;

public class JSONParser {
    public static void main(String[] args) {
        System.exit(0);
    }

    // TODO: find

    // TODO: replace

    // TODO: sort

    // TODO: append

    // TODO: remove

    // TODO: rename

    // TODO: toArray

    // TODO: arrayToMap

    // TODO: mapToArray

    public static String toSingleLine(String directory) {
        return FileHandler.toSingleLine(directory);
    }

    private static String newLine(int tabCount) {
        return "\n" + "\t".repeat(Math.max(0, tabCount - 1));
    }

    public static String beautifier(String directory) {
        String json = FileHandler.toSingleLine(directory);
        int tabCount = 0;

        StringBuilder jsonBuilder = new StringBuilder();
        char[] inputChar = json.toCharArray();

        for (int i = 0; i < inputChar.length; i++) {
            String charI = String.valueOf(inputChar[i]);
            if (charI.equals("}") || charI.equals("]")) {
                tabCount--;
                if (!String.valueOf(inputChar[i - 1]).equals("[") && !String.valueOf(inputChar[i - 1]).equals("{")) {
                    jsonBuilder.append(newLine(tabCount));
                }
            }
            jsonBuilder.append(charI);

            if (charI.equals("{") || charI.equals("[")) {
                tabCount++;
                if (String.valueOf(inputChar[i + 1]).equals("]") || String.valueOf(inputChar[i + 1]).equals("}")) {
                    continue;
                }

                jsonBuilder.append(newLine(tabCount));
            }

            if (charI.equals(",")) {
                jsonBuilder.append(newLine(tabCount));
            }
        }

        return jsonBuilder.toString();
    }
}