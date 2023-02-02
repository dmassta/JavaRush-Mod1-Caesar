package com.formatiqa.dmassta;

import java.io.IOException;
import java.util.Scanner;

/*/Users/dmassta/Documents/Java/input.txt
 * */

public class CaesarCypher {
    public static void main(String[] args) throws IOException {

        Encryption encryption = new Encryption();
        Decryption decryption = new Decryption();
        BruteForce bruteForce = new BruteForce();

        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to do with text? " +
                "Write one of the commands and press enter (encode | decode | brute): ");

        while(true) {
            if (scanner.hasNext("encode")) {
                encryption.encryptText();
                break;
            } else if (scanner.hasNext("decode")) {
                decryption.decryptText();
                break;
            } else if (scanner.hasNext("brute")) {
                bruteForce.bruteForceDecode();
                break;
            } else {
                System.out.print("Wrong command. Enter correct command: ");
                scanner.nextLine();
            }
        }
    }
}