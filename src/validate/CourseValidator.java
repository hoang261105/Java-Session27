package validate;

import entity.Course;
import presentation.UniversityManager;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CourseValidator {
    public static String validateInputCourseId(Scanner sc, String message, String regex) {
        System.out.println(message);

        do {
            try {
                String input = sc.nextLine();

                if(Pattern.matches(regex, input)) {
                    return input;
                }

                System.err.println("Dữ liệu nhập không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static String validateExist(Scanner sc, String value, String type){
        do {
            boolean exist = false;
            switch (type) {
                case "courseId":
                    String courseId = value;
                    List<Course> existCourseId = UniversityManager.courseList.stream()
                            .filter(course -> course.getCourseId().equals(courseId))
                            .toList();

                    if (!existCourseId.isEmpty()){
                        exist = true;
                    }
                    break;
                case "courseName":
                    String courseName = value;
                    List<Course> existCourseName = UniversityManager.courseList.stream()
                            .filter(course -> course.getCourseName().equals(courseName))
                            .toList();

                    if (!existCourseName.isEmpty()){
                        exist = true;
                    }
                    break;
            }


            if (!exist){
                return value;
            }

            System.err.println("Dữ liệu đã tồn tại. Vui lòng nhập lại");
            value = sc.nextLine();
        }while (true);
    }
}
