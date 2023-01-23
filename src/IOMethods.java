import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class IOMethods {

    final private static String encrypted = "Encrypted";

    public static String getNewFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + encrypted + oldFileName.substring(dotIndex);
    }

    protected static Path pathToFile() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path to a text file: ");
        Path path = Path.of(sc.nextLine());
        while (true) {
            if (!(Files.exists(path) && Files.isRegularFile(path))) {
                System.out.print("Path is incorrect. Please enter again: ");
                path = Path.of(sc.nextLine());
            } else {
                break;
            }
        }
        return path;
    }

    protected static int encryptionKey() {
        int shift;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter an integer: ");
            if (sc.hasNextInt()) {
                shift = sc.nextInt();
                break;
            } else {
                System.out.print("Invalid input. Please enter an integer: ");
                sc.next();
            }
        }
        return shift;
    }

//    protected static void writeFile(Path path) {
//        Path destFile = Path.of(IOMethods.getNewFileName(String.valueOf(IOMethods.pathToFile().getFileName())));
//        Files.createFile(destFile, )
//    }
}
