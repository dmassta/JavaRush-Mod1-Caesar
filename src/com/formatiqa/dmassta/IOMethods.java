package com.formatiqa.dmassta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class IOMethods {

    public String getEncryptedFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        String encrypted = "Encrypted";
        return oldFileName.substring(0, dotIndex) + encrypted + oldFileName.substring(dotIndex);
    }
    public String getDecryptedFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        String decrypted = "Decrypted";
        return oldFileName.substring(0, dotIndex) + decrypted + oldFileName.substring(dotIndex);
    }

    protected Path getPathToFile() {//метод обрабатывает любой неверно введеный путь к файлу
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path to a text file: ");
        Path path = Path.of(sc.nextLine());
        while (true) {
            if (!(Files.exists(path) && Files.isRegularFile(path))) {
                System.out.print("Path is incorrect. Please enter again: ");
                path = Path.of(sc.nextLine());
            } else {
                break;
            }
        }
        return path;
    }

    protected int getEncryptionKey() {
        int key;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a key: ");
            if (sc.hasNextInt()) {
                key = sc.nextInt();
                break;
            } else {
                System.out.print("Invalid input. Please enter an integer: ");
                sc.next();
            }
        }
        return key;
    }

    protected ArrayList<String> readFile(Path path) throws IOException{
        return (ArrayList<String>) Files.readAllLines(path);
    }

    protected Path createFile(Path path, String fileName) throws IOException{
        File parentDirectory = new File(path.toUri()).getParentFile();
        File newFile = new File(parentDirectory, fileName);
        if (newFile.createNewFile()) {
            System.out.println("Created file in: "
                    + Path.of(newFile.getAbsolutePath()).toUri());
        } else {
            System.out.println("File already exists: " + Path.of(newFile.getAbsolutePath()).toUri());
        }
        return newFile.toPath();
    }

    protected void writeFile(Path path, ArrayList<String> list) {
        try (BufferedWriter destFileWriter = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String str : list) {
                destFileWriter.write(str, 0, str.length());
                destFileWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path.getFileName() + " is written with processed text.\n");
    }
}
