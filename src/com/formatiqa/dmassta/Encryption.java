package com.formatiqa.dmassta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Encryption {

    char encode(char sourceChar, int key) {

        char cipherChar = 0;
        Character plainChar = sourceChar;
        Alphabet alphabet = new Alphabet();
        String alphabetLower = alphabet.getAlphabetLowerCase();
        int lowerLength = alphabet.getAlphabetLowerCase().length();
        String alphabetUpper = alphabet.getAlphabetUpperCase();
        int upperLength = alphabet.getAlphabetUpperCase().length();
        char[] alphabetSigns = alphabet.getAlphabetSigns();
        int signsLength = alphabet.getAlphabetSigns().length;
        boolean plainIsLetter = alphabet.isAlphabetLetter(plainChar);
        boolean plainIsSign = alphabet.isAlphabetSigns(plainChar);

        if (plainIsLetter) {
            for (int i = 0; i < lowerLength; i++) {
                boolean plainIsLower = plainChar.equals(alphabetLower.charAt(i));
                boolean plainIsUpper = plainChar.equals(alphabetUpper.charAt(i));
                if (plainIsLower) {
                    int index = (i + key) % lowerLength;
                    if (index < 0) {
                        index += lowerLength;
                    }
                    cipherChar = alphabetLower.charAt(index);
                    break;
                } else if (plainIsUpper) {
                    int index = (i + key) % upperLength;
                    if (index < 0) {
                        index += upperLength;
                    }
                    cipherChar = alphabetUpper.charAt(index);
                    break;
                }
            }
            return cipherChar;
        } else if (plainIsSign) {
            for (int i = 0; i < signsLength; i++) {
                if (plainChar == alphabetSigns[i]) {
                    int index = (i + key) % signsLength;
                    if (index < 0) {
                        index += signsLength;
                    }
                    cipherChar = alphabetSigns[index];
                    break;
                }
            }
            return cipherChar;
        } else {
            return sourceChar;
        }
    }

    protected void encryptText() throws IOException {

        IOMethods ioMethods = new IOMethods();
        Path inputPath = ioMethods.getPathToFile();
        int key = ioMethods.getEncryptionKey();
        System.out.println("----------------------------\n");

        String plainText = Files.readString(inputPath);
        StringBuilder encodedStr = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            encodedStr.append(encode(plainText.charAt(i), key));
        }

        String encryptedFileName = ioMethods.getEncryptedFileName(String.valueOf(inputPath.getFileName()));
        Path encryptedFile = ioMethods.createFile(inputPath, encryptedFileName);
        ioMethods.writeFile(encryptedFile, encodedStr.toString());

        System.out.println("Encrypted text:");

        try (var lines = Files.lines(encryptedFile)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("""
                ----------------------------
                                
                """);
    }
}
