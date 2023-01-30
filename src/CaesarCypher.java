import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/*/Users/dmassta/Documents/Java/input.txt
* */

public class CaesarCypher {
    public static void main(String[] args) throws IOException {

        Path path = IOMethods.pathToFile();//получаем путь к файлу
        int key = IOMethods.encryptionKey();//получаем ключ
        System.out.println("----------------------------\n");

        ArrayList<String> list = IOMethods.readFile(path);//читаем строки текста из файла
        ArrayList<String> encodedList = new ArrayList<>();//список для зашифрованного текста
        ArrayList<String> decodedList = new ArrayList<>();//список для расшифрованного текста

        //for each цикл по каждой строке исходного списка текста
        for (String str : list) {
            StringBuilder encodedStr = new StringBuilder();//новая строка для шифрованного текста
            for (int i = 0; i < str.length(); i++) {
                //шифруем каждый знак и сразу вставляем в строку
                encodedStr.append(Encrypt.encode(str.charAt(i), key));
            }
            encodedList.add(String.valueOf(encodedStr));//добавляем строку к конечному списку зашифрованных строк
        }

        String encryptedFileName = IOMethods.getEncryptedFileName(String.valueOf(path.getFileName()));
        Path encryptedFile = IOMethods.createFile(path, encryptedFileName);//создаем новый файл
        IOMethods.writeFile(encryptedFile, encodedList);//пишем полученные зашифрованные строки в файл
        System.out.println("Encrypted text:");

        try (var lines = Files.lines(encryptedFile)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("""
                ----------------------------
                
                """);

        for (String str : encodedList) {
            StringBuilder decodedStr = new StringBuilder();//новая пустая строка для шифрованного текста
            for (int i = 0; i < str.length(); i++) {
                //шифруем каждый знак и сразу вставляем в строку
                decodedStr.append(Encrypt.decode(str.charAt(i), key));
            }
            decodedList.add(String.valueOf(decodedStr));//добавляем строку к конечному списку зашифрованных строк
        }

        String decryptedFileName = IOMethods.getDecryptedFileName(String.valueOf(path.getFileName()));
        Path decryptedFile = IOMethods.createFile(path, decryptedFileName);
        IOMethods.writeFile(decryptedFile, decodedList);
        System.out.println("Decrypted text:");

        try (var lines = Files.lines(decryptedFile)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("""
                ----------------------------
                """);
    }
}