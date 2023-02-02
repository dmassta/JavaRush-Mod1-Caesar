package com.formatiqa.dmassta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class Decryption {

    char decode(char cipherChar, int key) {
        Encryption encryption = new Encryption();
        return encryption.encode(cipherChar, -key);
    }

    protected void decryptText() throws IOException {

        IOMethods ioMethods = new IOMethods();
        Path path = ioMethods.getPathToFile();
        int key = ioMethods.getEncryptionKey();
        System.out.println("----------------------------\n");

        ArrayList<String> list = ioMethods.readFile(path);
        ArrayList<String> decodedList = new ArrayList<>();

        for (String str : list) {
            StringBuilder decodedStr = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                decodedStr.append(decode(str.charAt(i), key));
            }
            decodedList.add(String.valueOf(decodedStr));
        }

        String decryptedFileName = ioMethods.getDecryptedFileName(String.valueOf(path.getFileName()));
        Path decryptedFile = ioMethods.createFile(path, decryptedFileName);
        ioMethods.writeFile(decryptedFile, decodedList);

        System.out.println("Decrypted text:");

        try (var lines = Files.lines(decryptedFile)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("""
                ----------------------------
                """);
    }
}
