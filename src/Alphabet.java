public class Alphabet {
    protected static final String alphabetLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    protected static final String alphabetUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

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
        Character character = ch;

        boolean result = false;

        for (int i = 0; i < sb.length(); i++) {
            if (character.equals(sb.charAt(i))) {
                result = true;
            }
        }
        return result;
    }

    protected static boolean isAlphabetSigns(char ch) {
        boolean result = false;
        Character character = ch;
        for (int i = 0; i < getAlphabetSigns().length; i++) {
            if (character.equals(getAlphabetSigns()[i])) {
                result = true;
            }
        }
        return result;
    }
}
