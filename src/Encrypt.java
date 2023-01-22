import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypt {

    static Path encryptText(Path path, int shift) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path to a text file: ");
        Path sourceFile = Path.of(sc.nextLine());

        if (!sourceFile.isAbsolute()) {
            System.out.print("Path is incorrect. Please enter again: ");
            sc.nextLine();
        }

        Path destFile = Path.of(getNewFileName(String.valueOf(sourceFile.getFileName())));
        byte[] sourceBytes = Files.readAllBytes(sourceFile);

        for (int i = 0; i < sourceBytes.length; i++) {
            char ch = (char) sourceBytes[i];
            System.out.println(ch);
        }

//        String cipherText = "";
//        for (int i = 0; i < plainText.length(); i++) {
//            char ch = plainText.charAt(i);
//            if (Character.isLetter(ch)) {
//                ch = (char) (plainText.charAt(i) + shift);
//                if (Character.isUpperCase(plainText.charAt(i)) && ch > 'Z'
//                        || Character.isLowerCase(plainText.charAt(i)) && ch > 'z') {
//                    ch = (char) (plainText.charAt(i) - (26 - shift));
//                }
//            }
//            cipherText += ch;
//        }
        return destFile;
    }

    static String decryptText(String cipherText, int shift) {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                ch = (char) (cipherText.charAt(i) - shift);
                if (Character.isUpperCase(cipherText.charAt(i)) && ch < 'A'
                        || Character.isLowerCase(cipherText.charAt(i)) && ch < 'a') {
                    ch = (char) (cipherText.charAt(i) + (26 - shift));
                }
            }
            plainText += ch;
        }
        return plainText;
    }
}
