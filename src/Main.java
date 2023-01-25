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

        //for each цикл по каждой строке исходного списка текста
        for (String str : list) {
            StringBuilder encodedStr = new StringBuilder();//новая пустая строка для шифрованного текста
            for (int i = 0; i < str.length(); i++) {
                //шифруем каждый знак и сразу вставляем в строку
                encodedStr.append(Encrypt.encode(str.charAt(i), key));
            }
            encodedList.add(String.valueOf(encodedStr));//добавляем строку к конечному списку зашифрованных строк
        }

        System.out.println(encodedList);

    }
}