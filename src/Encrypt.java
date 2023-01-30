import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Encrypt {

    /*
    encode(sourceChar, key) обрабатывает и шифрует одну букву/знак, входящие в Alphabet,
    заданный в отдельном классе Alphabet.class, метод возвращает зашифрованный char с помощью ключа key
     */
    protected static char encode(char sourceChar, int key) {

        char cipherChar = 0;
        Character plainChar = sourceChar;
        String alphabetLower = Alphabet.getAlphabetLowerCase();
        int lowerLength = Alphabet.getAlphabetLowerCase().length();
        String alphabetUpper = Alphabet.getAlphabetUpperCase();
        int upperLength = Alphabet.getAlphabetUpperCase().length();
        char[] alphabetSigns = Alphabet.getAlphabetSigns();
        int signsLength = Alphabet.getAlphabetSigns().length;
        boolean plainIsLetter = Alphabet.isAlphabetLetter(plainChar);
        boolean plainIsSign = Alphabet.isAlphabetSigns(plainChar);

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
        } else
            if (plainIsSign) {
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

    /*метод decode(cipherChar, key) декодирует входящий char
    с помощью инвертированного ключа метода encode(plainChar, key)
    * */
    protected static char decode(char cipherChar, int key) {
        return encode(cipherChar, -key);
    }

}
