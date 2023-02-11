package com.formatiqa.dmassta;

import java.io.IOException;
import java.util.Scanner;

/*plain  /Users/dmassta/Documents/Java/input.txt
encrypted /Users/dmassta/Documents/Java/inputEncrypted.txt
 * */

public class CaesarCypher {
    public static void main(String[] args) throws IOException {

        Encryption encryption = new Encryption();
        Decryption decryption = new Decryption();
        BruteForce bruteForce = new BruteForce();

        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to do with text?\n" +
                "Write one of the commands and press enter ([e]ncode | [d]ecode | [b]rute force): ");

        while(true) {
            if (scanner.hasNext("encode") || scanner.hasNext("e")) {
                encryption.encryptText();
                break;
            } else if (scanner.hasNext("decode") || scanner.hasNext("d")) {
                decryption.decryptText();
                break;
            } else if (scanner.hasNext("brute force") || scanner.hasNext("b")) {
                bruteForce.decodeBruteForce();
                break;
            } else {
                System.out.print("Wrong command. Enter correct command: ");
                scanner.nextLine();
            }
        }
    }
}