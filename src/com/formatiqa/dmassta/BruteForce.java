package com.formatiqa.dmassta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class BruteForce {

    static Alphabet alphabet = new Alphabet();
    private static String alphabetLower = alphabet.getAlphabetLowerCase();
    private static int lowerLength = alphabetLower.length();

    public void decodeBruteForce() throws IOException {

        IOMethods ioMethods = new IOMethods();
        Decryption decryption = new Decryption();
        Path filePath = ioMethods.getPathToFile();
        System.out.println("-----------------------");

        String decodedText = "";
        int decryptionKey = 0;
        String encryptedText = Files.readString(filePath);

        for (int key = 1; key <= lowerLength; key++) {
            StringBuilder decodedStr = new StringBuilder();
            for (char c : encryptedText.toCharArray()) {
                decodedStr.append(decryption.decode(c, key));
            }
            if (decodedStr.toString().contains(", но")
                    || decodedStr.toString().contains(", а")
                    || decodedStr.toString().contains(", чтобы")
                    || decodedStr.toString().contains(", как")
                    || decodedStr.toString().contains(", чем")
                    || decodedStr.toString().contains(", что")
                    || decodedStr.toString().contains(", который")) {
                decodedText = decodedStr.toString();
                decryptionKey = key;
                System.out.println("Key is: " + decryptionKey + "\n");
                System.out.println("Decoded text is:\n" + decodedText);
                break;
            }
        }

        Path outputPath = ioMethods.createFile(filePath, "bruteForce_" + filePath.getFileName());
        ioMethods.writeFile(outputPath, decodedText);
        System.out.println("Path to brute force decoded file is " + outputPath.toUri());
    }
}
