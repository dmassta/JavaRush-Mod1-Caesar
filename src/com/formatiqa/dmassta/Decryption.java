package com.formatiqa.dmassta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        String encryptedText = Files.readString(path);
        StringBuilder decodedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            decodedText.append(decode(encryptedText.charAt(i), key));
        }

        String decryptedFileName = ioMethods.getDecryptedFileName(String.valueOf(path.getFileName()));
        Path decryptedFile = ioMethods.createFile(path, decryptedFileName);
        ioMethods.writeFile(decryptedFile, decodedText.toString());

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
