package entity;

import validate.StudentValidator;
import validate.Validator;

import java.util.Scanner;

public class Student extends Person implements IApp {
    private String studentId;
    private double gpa;

    public Student(String name, int age, String address, String phoneNumber, String email, Gender gender) {
        super(name, age, address, phoneNumber, email, gender);
    }

    public Student(String name, int age, String address, String phoneNumber, String email, String studentId, double gpa) {
        super(name, age, address, phoneNumber, email);
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public void inputData(Scanner sc) {
        this.studentId = inputStudentId(sc);

        super.inputData(sc);

        this.gpa = Validator.validateInputDouble(sc, "Nhập điểm trung binh");
    }

    private String inputStudentId(Scanner sc) {
        String studentId = StudentValidator.inputStudentId(sc, "Nhập id sinh viên: ", "^(SV).{3}$");
        return StudentValidator.validateExistStudentId(sc, studentId);
    }

    @Override
    public String toString() {
        return "ID: " + studentId + super.toString() + ", GPA: " + gpa;
    }
}
