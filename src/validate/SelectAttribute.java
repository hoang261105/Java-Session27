package validate;

import presentation.UniversityManager;

import java.util.Scanner;

public class SelectAttribute {
    public static String selectStudentById(Scanner sc) {
        do {
            System.out.println("------------------DANH SÁCH SINH VIÊN------------------");
            UniversityManager.studentList.forEach(student -> {
                System.out.printf("%s. %s\n", student.getStudentId(), student.getName());
            });
            System.out.printf("Lựa chọn của bạn (Enter để bỏ qua): ");

            String studentId = sc.nextLine().trim();

            boolean isExist = UniversityManager.studentList.stream().anyMatch(student -> student.getStudentId().equals(studentId));

            if (isExist) {
                return studentId;
            }

            System.out.println("ID sinh viên không tồn tại! Vui lòng nhập lại.");
        }while (true);
    }

    public static String selectCourseById(Scanner sc) {
        do {
            System.out.println("------------------DANH SÁCH KHÓA HỌC------------------");
            UniversityManager.courseList.forEach(course -> {
                System.out.printf("%s. %s\n", course.getCourseId(), course.getCourseName());
            });
            System.out.printf("Lựa chọn của bạn: ");

            String courseId = sc.nextLine().trim();

            if (courseId.isEmpty()) {
                return "Chưa có";
            }

            boolean isExist = UniversityManager.courseList.stream().anyMatch(course -> course.getCourseId().equals(courseId));

            if (isExist) {
                return courseId;
            }

            System.out.println("ID khóa học không tồn tại! Vui lòng nhập lại.");
        }while (true);
    }

    public static int selectTeacherById(Scanner sc) {
        do {
            System.out.println("------------------DANH SÁCH GIẢNG VIÊN------------------");
            UniversityManager.teacherList.forEach(teacher -> {
                System.out.printf("%d. %s\n", teacher.getTeacherId(), teacher.getName());
            });
            System.out.printf("Lựa chọn của bạn (Enter để bỏ qua): ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return 0;
            }

            try {
                int teacherId = Integer.parseInt(input);
                boolean isExist = UniversityManager.teacherList.stream()
                        .anyMatch(teacher -> teacher.getTeacherId() == teacherId);

                if (isExist) {
                    return teacherId;
                }

                System.out.println("ID giảng viên không tồn tại! Vui lòng nhập lại.");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ hoặc Enter để bỏ qua.");
            }
        }while (true);
    }

    public static int selectClassroomById(Scanner sc) {
        do {
            System.out.println("------------------DANH SÁCH LỚP HỌC------------------");
            UniversityManager.classRoomList.forEach(classroom -> {
                System.out.printf("%d. %s\n", classroom.getClassroomId(), classroom.getClassroomName());
            });
            System.out.printf("Lựa chọn của bạn: ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return 0;
            }

            try {
                int classroomId = Integer.parseInt(input);

                boolean isExist = UniversityManager.classRoomList.stream().anyMatch(classroom -> classroom.getClassroomId() == classroomId);

                if (isExist) {
                    return classroomId;
                }

                System.err.println("Id lớp học không tồn tại. Vui lòng nhập lại!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ hoặc Enter để bỏ qua.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }
}
