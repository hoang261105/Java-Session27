package presentation;

import business.model.Student;
import business.service.student.StudentService;
import business.service.student.StudentServiceImp;

import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private final StudentService studentService;

    public StudentUI() {
        studentService = new StudentServiceImp();
    }

    public static void displayStudentMenu(Scanner sc) {
        StudentUI studentUI = new StudentUI();
        do {
            System.out.println("-------------------STUDENT MANAGEMENT----------------");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Thống kê sinh viên theo tuổi");
            System.out.println("6. Quay lại menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    studentUI.displayStudents();
                    break;
                case 2:
                    studentUI.addStudent(sc);
                    break;
                case 3:
                    studentUI.updateStudent(sc);
                    break;
                case 4:
                    studentUI.deleteStudent(sc);
                    break;
                case 5:
                    studentUI.countStudentByAge(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 6");
            }
        } while (true);
    }

    public void displayStudents(){
        List<Student> studentList = studentService.findAll();

        studentList.forEach(System.out::println);
    }

    public void addStudent(Scanner sc) {
        System.out.println("Nhập vào số sinh viên cần thêm:");
        int size = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < size; i++){
            Student student = new Student();
            student.inputData(sc);
            boolean result = studentService.save(student);
            if(result){
                System.out.println("Thêm sinh viên thành công!");
            }else{
                System.err.println("Thêm thất bại!");
            }
        }
    }

    public void updateStudent(Scanner sc){
        System.out.println("Nhap vao mã sinh viên cần cập nhật:");
        int studentId = Integer.parseInt(sc.nextLine());

        if(studentService.findById(studentId) != null){
            Student student = new Student();
            student.setId(studentId);
            student.inputData(sc);

            boolean result = studentService.update(student);
            if(result){
                System.out.println("Cập nhật thành công!");
            }else{
                System.err.println("Cập nhật thất bại!");
            }
        }else{
            System.err.println("Không tồn tại mã sinh viên");
        }
    }

    public void deleteStudent(Scanner sc){
        System.out.println("Nhập vào id cần xóa:");
        int id = Integer.parseInt(sc.nextLine());

        if (studentService.findById(id) != null) {
            Student student = new Student();
            student.setId(id);
            boolean result = studentService.delete(student);

            if(result){
                System.out.println("Xóa sinh viên thành công!");
            }else{
                System.err.println("Xóa thất bại!");
            }
        }else{
            System.err.println("Không tìm thấy sinh viên!");
        }
    }

    public void countStudentByAge(Scanner sc) {
        System.out.println("Nhập tuổi cần thống kê");
        int age = Integer.parseInt(sc.nextLine());

        System.out.printf("Có %d sinh viên ở tuổi %d\n", studentService.getCountStudentByAge(age), age);
    }
}
