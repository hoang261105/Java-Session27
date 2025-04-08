package business;

import entity.Course;
import entity.CourseRegistration;
import entity.Student;
import presentation.UniversityManager;

import java.util.List;
import java.util.Scanner;

public class CourseRegistrationBusiness {
    public static void displayCourseRegistrationMenu(Scanner sc) {
        CourseRegistrationBusiness courseRegistrationBusiness = new CourseRegistrationBusiness();
        do {
            System.out.println("*******************COURSE REGISTRATION MENU*****************");
            System.out.println("1. Đăng ký khóa học cho sinh viên");
            System.out.println("2. Hủy đăng ký học cho sinh viên (Chỉ được hủy nếu trạng thái là PENDING)");
            System.out.println("3. Duyệt đăng ký học sinh viên (PENDING → ENROLLED)");
            System.out.println("4. Xem danh sách sinh viên đăng ký từng khóa học");
            System.out.println("5. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    courseRegistrationBusiness.registerCourse(sc);
                    break;
                case 2:
                    courseRegistrationBusiness.cancelCourseRegistration(sc);
                    break;
                case 3:
                    courseRegistrationBusiness.displayCourseRegistration(sc);
                    break;
                case 4:
                    courseRegistrationBusiness.displayStudentByCourse();
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 5!");
            }
        } while (true);
    }

    public void registerCourse(Scanner sc) {
        System.out.println("Nhập vào số sinh viên cần đăng ký khóa học");

        int size = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < size; i++) {
            System.out.println("Nhập thông tin đăng ký khóa học thứ " + (i + 1));
            CourseRegistration courseRegistration = new CourseRegistration();
            courseRegistration.inputData(sc);
            UniversityManager.courseRegistrationList.add(courseRegistration);
            System.out.println("Đăng kí khóa học thanh công!");
        }
    }

    public void cancelCourseRegistration(Scanner sc) {
        System.out.println("Nhập vào id đăng ký khóa học cần hủy:");
        int crid = Integer.parseInt(sc.nextLine());

        int indexCancel = findCourseRegisterById(crid);

        if (indexCancel == -1) {
            System.err.println("Không tìm thấy bản đăng ký tương ứng!");
            return;
        }

        CourseRegistration crg = UniversityManager.courseRegistrationList.get(indexCancel);

        if (crg.getStatus() != CourseRegistration.Status.PENDING) {
            System.err.println("Chỉ có thể hủy các đơn đăng ký đang chờ xử lý (PENDING)!");
            return;
        }

        UniversityManager.courseRegistrationList.remove(indexCancel);
        System.out.println("Đã hủy đăng ký khóa học thành công!");
    }

    public void displayCourseRegistration(Scanner sc) {
        if(UniversityManager.courseRegistrationList.isEmpty()) {
            System.err.println("Danh sách trống. Không thể duyệt!");
            return;
        }

        System.out.println("Nhập vào id đăng ký khóa học cần duyệt:");
        int crid = Integer.parseInt(sc.nextLine());

        int indexFind = findCourseRegisterById(crid);
        if (indexFind == -1) {
            System.err.println("Không tìm thấy bản đăng ký tương ứng!");
            return;
        }

        CourseRegistration crg = UniversityManager.courseRegistrationList.get(indexFind);
        if (crg.getStatus() != CourseRegistration.Status.PENDING) {
            System.err.println("Bản đăng ký đã được duyệt!");
            return;
        }

        UniversityManager.courseRegistrationList.get(indexFind).setStatus(CourseRegistration.Status.ENROLLED);
        System.out.println("Duyệt thành công!");
    }

    public void displayStudentByCourse(){
        List<Course> listCourse = UniversityManager.courseList;

        List<Student> listStudents = UniversityManager.studentList;

        for(Course course : listCourse) {
            System.out.println("Danh sách sinh viên đăng ký khóa học: " + course.getCourseName());

            List<CourseRegistration> filteredStudents = UniversityManager.courseRegistrationList.stream()
                    .filter(courseRegistration -> courseRegistration.getCourseId().equals(course.getCourseId()))
                    .toList();

            if (filteredStudents.isEmpty()) {
                System.err.println("Không có sinh viên đăng ký khóa học này!");
                return;
            }

            for(CourseRegistration crg : filteredStudents){
                String studentId = crg.getStudentId();

                Student student = findStudentById(listStudents, studentId);

                if (student == null) {
                    System.err.println("Không tìm thấy thông tin sinh viên với ID: " + studentId);
                    return;
                }

                System.out.println("Sinh viên: " + student.getName() + ", Mã sinh viên: " + student.getStudentId());
            }
        }
    }

    public Student findStudentById(List<Student> students, String studentId) {
        for(Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public int findCourseRegisterById(int id) {
        for (int i = 0; i < UniversityManager.courseRegistrationList.size(); i++) {
            if (UniversityManager.courseRegistrationList.get(i).getCrId() == id) {
                return i;
            }
        }
        return -1;
    }
}
