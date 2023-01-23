import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Encrypt {

    static List<String> encryptText(Path path, int key) throws IOException {

        List<String> sourceText = Files.readAllLines(path);
        List<String> cipherText;


        for (String plainText : sourceText) {
            StringBuilder cipherTextLine = new StringBuilder();
            for (int i = 0; i < plainText.length(); i++) {
                char ch = plainText.charAt(i);
                char cipherCh = '0';
                if (Alphabet.isAlphabetLetter(ch)) {
                    if (Alphabet.isAlphabetLower(ch) && key > 'я') {
                        cipherCh = alphabetLower.charAt(alphabetLower.indexOf(ch) - (lowerLength - key));
                    } else
                        if (Alphabet.isAlphabetUpper(ch)) {
                            cipherCh = alphabetUpper.charAt(alphabetUpper.indexOf(ch + key));
                        }
                } else
                    if (Alphabet.isAlphabetSigns(ch)) {

                    }
                cipherTextLine.append(cipherCh);
            }
        }

        return ;
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
