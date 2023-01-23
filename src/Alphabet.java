public class Alphabet {
    protected static final String alphabetLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    protected static final String alphabetUpperCase = alphabetLowerCase.toUpperCase();

    protected static final char[] alphabetSigns = new char[] {'.', ',', '"', ':', '-', '!', '?', ' '};

    protected static String getAlphabetLowerCase() {
        return alphabetLowerCase;
    }

    protected static String getAlphabetUpperCase() {
        return alphabetUpperCase;
    }

    protected static char[] getAlphabetSigns() {
        return alphabetSigns;
    }

    protected static boolean isAlphabetLetter(char ch) {

        StringBuilder sb = new StringBuilder();
        sb.append(getAlphabetLowerCase());
        sb.append(getAlphabetUpperCase());

        boolean result = false;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ch) {
                result = true;
            }
        }
        return result;
    }

    protected static boolean isAlphabetLower(char ch) {
        boolean result = false;
        for (int i = 0; i < getAlphabetLowerCase().length(); i++) {
            if (getAlphabetLowerCase().charAt(i) == ch) {
                result = true;
            }
        }
        return result;
    }

    protected static boolean isAlphabetUpper(char ch) {
        boolean result = false;
        for (int i = 0; i < getAlphabetUpperCase().length(); i++) {
            if (getAlphabetUpperCase().charAt(i) == ch) {
                result = true;
            }
        }
        return result;
    }

    protected static boolean isAlphabetSigns(char ch) {
        boolean result = false;
        for (int i = 0; i < getAlphabetSigns().length; i++) {
            if (getAlphabetSigns()[i] == ch) {
                result = true;
            }
        }
        return result;
    }
}
