package bt2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentListToXML {
    public static void main(String[] args) {
        // Tạo danh sách sinh viên
        List<Student> students = new ArrayList<>();
        students.add(new Student("Nguyen Van A", 20, 3.5));
        students.add(new Student("Tran Thi B", 22, 3.8));
        students.add(new Student("Le Van C", 21, 3.2));

        // Xuất danh sách sinh viên ra file XML
        try (FileWriter writer = new FileWriter("students.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<students>\n");

            for (Student student : students) {
                writer.write("    <student>\n");
                writer.write("        <name>" + student.getName() + "</name>\n");
                writer.write("        <age>" + student.getAge() + "</age>\n");
                writer.write("        <gpa>" + student.getGpa() + "</gpa>\n");
                writer.write("    </student>\n");
            }

            writer.write("</students>\n");
            System.out.println("Đã tạo file students.xml thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}