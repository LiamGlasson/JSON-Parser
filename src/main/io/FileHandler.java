/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║   File Handling Utility   ║
    ╚═══════════════════════════╝   */
package main.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    public static void create(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create the file.");
        }
    }

    public static void delete(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static String read(String fileName) {
        StringBuilder json = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return json.toString();
    }

    public static void write(String fileName, String content) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(content);
            file.close();
        } catch (IOException e) {
            System.out.println("Failed to write to the file.");
        }
    }

    public static String toSingleLine(String json) {
        json = json.replace(" ", "");
        json = json.replace("\n", "");
        json = json.replace("\t", "");
        json = json.replace("\u0009", "");
        return json;
    }
}