class EncodeChar {

    protected static char encode(char plainChar, int key) {

        char cipherChar;
        String alphabetLower = Alphabet.getAlphabetLowerCase();
        int lowerLength = Alphabet.getAlphabetLowerCase().length();
        String alphabetUpper = Alphabet.getAlphabetUpperCase();
        int upperLength = Alphabet.getAlphabetUpperCase().length();
        char[] alphabetSigns = Alphabet.getAlphabetSigns();
        int signsLength = Alphabet.getAlphabetSigns().length;

        while (key >= 33) {
            key -= 33;
        }

        while (key <= -33) {
            key -= -33;
        }

        if (key > 0 && Alphabet.isAlphabetLetter(plainChar)) {
            for (int i = 0; i < lowerLength; i++) {
                if (plainChar == alphabetLower.charAt(i) && (i + key >= lowerLength)) {
                    cipherChar = alphabetLower.charAt(alphabetLower.indexOf(i - (lowerLength - key)));
                } else
                    if (plainChar == alphabetUpper.charAt(i) && (i + key >= upperLength)) {
                        cipherChar = alphabetUpper.charAt(alphabetUpper.indexOf(i - (upperLength - key)));
                    }
            }
        }
    }
}
