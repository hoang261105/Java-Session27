package validate;

import entity.Student;
import presentation.UniversityManager;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentValidator {
    public static String inputStudentId(Scanner sc, String message, String regex) {
        System.out.println(message);

        do {
            try {
                String studentId = sc.nextLine();

                if (Pattern.matches(regex, studentId)) {
                    return studentId;
                }

                System.err.println("ID không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static String validateExistStudentId(Scanner sc, String studentId) {
        do {
            String finalStudentId = studentId;
            List<Student> existStudentId = UniversityManager.studentList.stream()
                    .filter(student -> student.getStudentId().equals(finalStudentId))
                    .toList();

            if(existStudentId.isEmpty()){
                return studentId;
            }

            System.err.println("ID đã tồn tại. Vui lòng nhập lại");
            studentId = sc.nextLine();
        }while (true);
    }
}
