import java.util.Locale;

public class Alphabet {
    protected static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    protected static final String alphabetUpperCase = alphabet.toUpperCase();

    protected static final char[] alphabetSigns = new char[] {'.', ',', '"', '?', '!', '-', ':', ';', ' '};

    protected static String getAlphabet() {
        return alphabet;
    }

    protected static String getAlphabetUpperCase() {
        return alphabetUpperCase;
    }

    protected static char[] getAlphabetSigns() {
        return alphabetSigns;
    }
}
