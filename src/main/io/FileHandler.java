/*  ╔═══════════════════════════╗
    ║ @author:     Liam Glasson ║
    ║ @version:      JDK 17.0.1 ║
    ╠═══════════════════════════╣
    ║ @framework:    JSON, Java ║
    ║   File Handling Utility   ║
    ╚═══════════════════════════╝   */
package main.io;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    public static String getTempDirectory() {
        return String.valueOf(System.getProperty("java.io.tmpdir"));
    }

    public static void create(String directory) {
        try {
            File file = new File(directory);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("IOException:");
            e.printStackTrace();
        }
    }

    public static void delete(String directory) {
        File file = new File(directory);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void write(String directory, String content) {
        try {
            FileWriter file = new FileWriter(directory);
            file.write(content);
            file.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("IOException:");
            e.printStackTrace();
        }
    }

    public static void read(String directory) {
        try {
            File file = new File(directory);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException:");
            e.printStackTrace();
        }
    }
}