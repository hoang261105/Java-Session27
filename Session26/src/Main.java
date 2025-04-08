import presentation.StudentUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("------------------APPLICATION MENU-----------------");
            System.out.println("1. Quản lí sinh viên");
            System.out.println("2. Quản lí lớp học");
            System.out.println("3. Thoát");
            System.out.printf("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    StudentUI.displayStudentMenu(sc);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn lại từ 1 - 3");
            }
        } while (true);
    }
}
