import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypt {

    final private static String encrypted = "Encrypted";

    public static String getNewFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + encrypted + oldFileName.substring(dotIndex);
    }

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
}