import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*/Users/dmassta/Documents/Java/input.txt
* */

public class Main {
    public static void main(String[] args) throws IOException {

        Path path = IOMethods.pathToFile();//получаем путь к файлу
        int key = IOMethods.encryptionKey();//получаем ключ
        ArrayList<String> list = IOMethods.readFile(path);//читаем строки текста из файла
        ArrayList<String> encodedList = new ArrayList<>();//список для зашифрованного текста
        ArrayList<String> decodedList = new ArrayList<>();

        //for each цикл по каждой строке исходного списка текста
        for (String str : list) {
            StringBuilder encodedStr = new StringBuilder();//новая пустая строка для шифрованного текста
            for (int i = 0; i < str.length(); i++) {
                //шифруем каждый знак и сразу вставляем в строку
                encodedStr.append(Encrypt.encode(str.charAt(i), key));
            }
            encodedList.add(String.valueOf(encodedStr));//добавляем строку к конечному списку зашифрованных строк
        }

        System.out.println(encodedList + "\n");

        for (String str : encodedList) {
            StringBuilder decodedStr = new StringBuilder();//новая пустая строка для шифрованного текста
            for (int i = 0; i < str.length(); i++) {
                //шифруем каждый знак и сразу вставляем в строку
                decodedStr.append(Encrypt.decode(str.charAt(i), key));
            }
            decodedList.add(String.valueOf(decodedStr));//добавляем строку к конечному списку зашифрованных строк
        }

        System.out.println(decodedList);
    }
}