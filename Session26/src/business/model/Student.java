package business.model;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void inputData(Scanner sc){
        System.out.println("Nhập tên: ");
        this.name = sc.nextLine();

        System.out.println("Nhập tuổi:");
        this.age = Integer.parseInt(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Mã sinh viên: " + id + " Tên: " + name + " Tuổi: " + age;
    }
}
