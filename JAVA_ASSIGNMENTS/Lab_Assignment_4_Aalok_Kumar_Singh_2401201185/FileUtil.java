import java.io.*;
import java.util.*;

public class FileUtil {

    public static List<Student> loadStudents(String filename) {
        List<Student> list = new ArrayList<>();

        try {
            File file = new File(filename);
            file.createNewFile();  // create if not exists

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(Student.fromCSV(line));
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return list;
    }

    public static void saveStudents(String filename, List<Student> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for (Student s : list) {
                bw.write(s.toCSV());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Demonstration of RandomAccessFile — read random line
    public static void readRandomRecord(String filename) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "r");
            long fileLength = raf.length();

            if (fileLength == 0) {
                System.out.println("File is empty!");
                raf.close();
                return;
            }

            long randomPos = (long) (Math.random() * (fileLength - 20));
            raf.seek(randomPos);

            System.out.println("\nRandomAccessFile Output:");
            System.out.println(raf.readLine());

            raf.close();
        } catch (Exception e) {
            System.out.println("RandomAccessFile Error: " + e.getMessage());
        }
    }
}
