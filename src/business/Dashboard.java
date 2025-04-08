package business;

import entity.*;
import presentation.UniversityManager;

import java.util.*;

public class Dashboard {
    public static void printDashboardMenu(Scanner sc) {
        Dashboard dashboard = new Dashboard();
        do {
            System.out.println("**************************DASHBOARD***********************");
            System.out.println("1. Thống kê số sinh viên, giảng viên, khóa học, lớp học");
            System.out.println("2. Top 3 khóa học có nhiều sinh viên nhất");
            System.out.println("3. Top 3 lớp học có nhiều sinh viên nhất");
            System.out.println("4. Top 3 giảng viên dạy nhiều sinh viên nhất");
            System.out.println("5. Top 3 sinh viên đăng ký học nhiều nhất");
            System.out.println("6. Trở về menu chính");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    dashboard.statisticDashboard();
                    break;
                case 2:
                    dashboard.top3CourseByStudent();
                    break;
                case 3:
                    dashboard.top3ClassRoomByStudent();
                    break;
                case 4:
                    dashboard.top3Teacher();
                    break;
                case 5:
                    dashboard.top3Students();
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 6!");
            }
        } while (true);
    }

    public void statisticDashboard() {
        int countStudents = UniversityManager.studentList.size();
        int countTeachers = UniversityManager.teacherList.size();
        int countCourses = UniversityManager.courseList.size();
        int countClassRooms = UniversityManager.classRoomList.size();

        System.out.println("Số sinh viên là: " + countStudents);
        System.out.println("Số giảng viên là: " + countTeachers);
        System.out.println("So khóa học là: " + countCourses);
        System.out.println("Số lớp học là: " + countClassRooms);
    }

    public void top3CourseByStudent() {
        List<Course> courseList = UniversityManager.courseList;
        List<Map.Entry<Course, Integer>> courseCountList = new ArrayList<>();

        for(Course course : courseList) {
            int countStudents = 0;
            for (CourseRegistration crgt : UniversityManager.courseRegistrationList) {
                if (course.getCourseId().equals(crgt.getCourseId())) {
                    countStudents++;
                }
            }

            courseCountList.add(Map.entry(course, countStudents));
        }

        System.out.println("Top 3 khóa học có số lượng sinh viên đăng ký nhiều nhất:");
        courseCountList.stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .limit(3)
                .forEach(entry -> {
                    System.out.printf("Khóa học: %s - Số lượng sinh viên: %d\n", entry.getKey().getCourseName(), entry.getValue());
                });
    }

    public void top3ClassRoomByStudent() {
        List<ClassRoom> classRoomList = UniversityManager.classRoomList;

        System.out.println("Top 3 lớp học có nhiều sinh viên nhất:");
        classRoomList.stream()
                .sorted((classroom1, classroom2) -> classroom2.getStudents().size() - classroom1.getStudents().size())
                .limit(3)
                .forEach(classRoom -> {
                    System.out.printf("Lớp học: %s - Số lượng sinh viên: %d\n", classRoom.getClassroomName(), classRoom.getStudents().size());
                });
    }

    public void top3Teacher(){
        List<Map.Entry<Integer, Integer>> teacherCountList = new ArrayList<>();

        for(ClassRoom classRoom : UniversityManager.classRoomList) {
            int teacherId = classRoom.getTeacherId();
            int countStudents = classRoom.getStudents().size();

            boolean teacherExists = false;
            for(Map.Entry<Integer, Integer> entry : teacherCountList){
                if(entry.getKey() == teacherId){
                    entry.setValue(entry.getValue() + countStudents);
                    teacherExists = true;
                    break;
                }
            }

            if(!teacherExists){
                teacherCountList.add(Map.entry(teacherId, countStudents));
            }
        }

        teacherCountList.stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .limit(3)
                .forEach(entry -> {
                    System.out.printf("Giảng viên: %s - Số lượng sinh viên: %d\n", findTeacherById(entry.getKey()), entry.getValue());
                });

    }

    public void top3Students(){
        List<Map.Entry<Student, Integer>> countStudentsList = new ArrayList<>();

        for (Student student : UniversityManager.studentList) {
            int countStudents = 0;

            for (CourseRegistration crgt : UniversityManager.courseRegistrationList) {
                if (student.getStudentId().equals(crgt.getStudentId())) {
                    countStudents++;
                }
            }

            countStudentsList.add(Map.entry(student, countStudents));
        }

        System.out.println("Top 3 sinh viên đăng ký nhiều khóa học nhất:");
        countStudentsList.stream()
                .sorted((student1, student2) ->student2.getValue() - student1.getValue())
                .limit(3)
                .forEach(entry -> {
                    System.out.printf("Sinh viên %s đăng ký %d khóa học\n", entry.getKey().getName(), entry.getValue());
                });
    }

    public String findTeacherById(int id){
        for(int i = 0; i < UniversityManager.teacherList.size(); i++) {
            if(id == UniversityManager.teacherList.get(i).getTeacherId()){
                return UniversityManager.teacherList.get(i).getName();
            }
        }
        return null;
    }
}
