package bt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryToXML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            try (FileWriter writer = new FileWriter("directory_structure.xml")) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                listDirectoryToXML(directory, writer, 0);
                System.out.println("Đã tạo file directory_structure.xml thành công.");
            } catch (IOException e) {
                System.out.println("Lỗi khi ghi file: " + e.getMessage());
            }
        } else {
            System.out.println("Thư mục không tồn tại hoặc không phải là thư mục hợp lệ.");
        }

        scanner.close();
    }

    private static void listDirectoryToXML(File directory, FileWriter writer, int indent) throws IOException {
        writeIndent(writer, indent);
        writer.write("<" + directory.getName() + ">\n");

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectoryToXML(file, writer, indent + 1);
                } else {
                    writeIndent(writer, indent + 1);
                    writer.write("<file>" + file.getName() + "</file>\n");
                }
            }
        }

        writeIndent(writer, indent);
        writer.write("</" + directory.getName() + ">\n");
    }

    private static void writeIndent(FileWriter writer, int indent) throws IOException {
        for (int i = 0; i < indent; i++) {
            writer.write("    ");
        }
    }
}