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

        if (plainIsLetter && key > 0) {//проверяем ключ и char на принадлежность
            while (key > (lowerLength - 1)) {//если ключ большой, то убираем лишние повторения
                key -= lowerLength;
            }
            for (int i = 0; i < lowerLength; i++) {

                boolean plainIsLower = plainChar.equals(alphabetLower.charAt(i));
                boolean plainIsUpper = plainChar.equals(alphabetUpper.charAt(i));
                //обрабатываем выход за пределы индекса алфавита
                if (plainIsLower && (i + key > (lowerLength - 1))) {//буквы нижнего регистра
                    cipherChar = alphabetLower.charAt(alphabetLower.indexOf(i - (lowerLength - key)));
                } else if (plainIsUpper && (i + key > (upperLength - 1))) {//верхний регистр
                    cipherChar = alphabetUpper.charAt(alphabetUpper.indexOf(i - (upperLength - key)));
                } else//шифруем простым сложением индекса и ключа, если их сумма не превысила длину алфавита
                    if (plainIsLower) {
                        cipherChar = alphabetLower.charAt(alphabetLower.indexOf(i + key));
                    } else if (plainIsUpper) {
                        cipherChar = alphabetUpper.charAt(alphabetUpper.indexOf(i + key));
                    }
            }
        } else if (plainIsLetter && key < 0) {//если ключ меньше 0
            while (key < (-upperLength + 1)) {//если ключ меньше отрицательной длины алфавита
                key -= -upperLength;//убираем лишние повторения
            }
            for (int i = 0; i < upperLength; i++) {

                boolean plainIsLower = plainChar.equals(alphabetLower.charAt(i));
                boolean plainIsUpper = plainChar.equals(alphabetUpper.charAt(i));
                //если сумма индекса и ключа меньше нуля и нижний регистр
                if (plainIsLower && (i + key < 0)) {
                    cipherChar = alphabetLower.charAt(alphabetLower.indexOf(i + (upperLength + key)));
                } else
                    //если сумма индекса и ключа меньше нуля и верхний регистр
                    if (plainIsUpper && (i + key < 0)) {
                        cipherChar = alphabetUpper.charAt(alphabetUpper.indexOf(i + (upperLength + key)));
                    } else
                        //простое сложение индекса и ключа, если их сумма не меньше нуля
                        if (plainIsLower) {
                            cipherChar = alphabetLower.charAt(i + key);
                        } else {
                            cipherChar = alphabetUpper.charAt(i + key);
                        }
            }
        } else
            //проверяем входящий char на равенство знаку препинания
            if (plainIsSign && key > 0) {
                while (key > signsLength - 1) {//тоже самое для ключа по длине алфавита знаков препинания
                    key -= signsLength;
                }
                for (int i = 0; i < signsLength; i++) {
                    if (i + key > (signsLength - 1)) {
                        cipherChar = alphabetSigns[i - (signsLength - key)];
                    } else {
                        cipherChar = alphabetSigns[i + key];
                    }
                }
            } else if (plainIsSign && key < 0) {
                while (key < (-signsLength + 1)) {
                    key -= -signsLength;
                }
                for (int i = 0; i < signsLength; i++) {
                    if (i + key < 0) {
                        cipherChar = alphabetSigns[i + (signsLength + key)];
                    } else {
                        cipherChar = alphabetSigns[i + key];
                    }
                }
            }
        //возвращаем зашифрованный char
        return cipherChar;
    }

    /*метод decode(cipherChar, key) декодирует входящий char
    с помощью инвертированного ключа метода encode(plainChar, key)
    * */
    protected static char decode(char cipherChar, int key) {
        char plainChar = encode(cipherChar, (-1 * key));
        return plainChar;
    }

    /*
    static List<String> encryptText(Path path, int key) throws IOException {


        List<String> sourceText = Files.readAllLines(path);
        List<String> cipherText = null;

        for (String plainText : sourceText) {
            StringBuilder cipherTextLine = new StringBuilder();
            for (int i = 0; i < plainText.length(); i++) {
                char ch = plainText.charAt(i);
                char cipherCh = EncodeChar.encode(ch, key);
                cipherTextLine.append(cipherCh);
            }
            assert false;
            cipherText.add(String.valueOf(cipherTextLine));
        }
        return cipherText;
    }*/

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
