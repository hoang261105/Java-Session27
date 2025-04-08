package entity;

import validate.CourseValidator;
import validate.StringRule;
import validate.Validator;

import java.util.Scanner;

public class Course implements IApp {
    private String courseId;
    private String courseName;
    private boolean status;

    public Course() {
    }

    public Course(String courseId, String courseName, boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.courseId = inputCourseId(sc);

        this.courseName = inputCourseName(sc);

        this.status = Validator.validateInputBoolean(sc, "Nhập trạng thái của khóa học:");
    }

    private String inputCourseId(Scanner sc) {
        String courseId = CourseValidator.validateInputCourseId(sc, "Nhap id của khóa học: ", "^[C].{4}$");
        return CourseValidator.validateExist(sc, courseId, "courseId");
    }

    private String inputCourseName(Scanner sc) {
        String courseName = Validator.validateInputString(sc, "Nhập tên khóa học: ", new StringRule(20, 100));
        return CourseValidator.validateExist(sc, courseName, "courseName");
    }

    public void displayData() {
        System.out.printf("Course ID: %s\n", courseId);
        System.out.printf("Course Name: %s\n", courseName);
        System.out.printf("Status: %s\n", status);
    }
}
