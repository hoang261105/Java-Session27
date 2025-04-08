package validate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Validator {
    public static String validateInputString(Scanner sc, String message, StringRule stringRule){
        System.out.println(message);

        do {
            try {
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    System.err.println("Lỗi: Chuỗi không được để trống. Vui lòng nhập lại!");
                    continue;
                }

                if(stringRule.isValid(input)){
                    return input;
                }

                System.err.println("Dữ liệu không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static double validateInputDouble(Scanner sc, String message){
        System.out.println(message);

        do {
            try {
                return Double.parseDouble(sc.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Dữ liệu số thực không hợp lệ. Vui lòng nhập lại");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static int validateInputInt(Scanner sc, String message){
        System.out.println(message);

        do {
            try {
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Dữ liệu số nguyên không hợp lệ. Vui lòng nhập lại");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static boolean validateInputBoolean(Scanner sc, String message){
        System.out.println(message);

        do {
            try {
                String input = sc.nextLine();

                if(input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")){
                    return Boolean.parseBoolean(input);
                }

                System.err.println("Dữ liệu boolean không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static LocalDate validateInputLocalDate(Scanner sc, String message){
        System.out.println(message);

        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(sc.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày tháng năm không hợp lệ. Vui lòng nhập lại");
            }catch (Exception e){
                e.printStackTrace();
            }
        }while (true);
    }

    public static LocalDateTime validateInputLocalDateTime(Scanner sc, String message){
        System.out.println(message);

        do {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(sc.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static <T extends Enum<T>> T validateEnumInput(Scanner sc, String message, Class<T> enumClass) {
        System.out.println(message + " " + Arrays.toString(enumClass.getEnumConstants())); // Hiển thị danh sách hợp lệ

        do {
            try {
                String input = sc.nextLine().trim().toUpperCase(); // Chuyển về chữ hoa để so sánh

                return Enum.valueOf(enumClass, input); // Chuyển đổi thành Enum (nếu hợp lệ)

            } catch (IllegalArgumentException e) {
                System.err.println("Lỗi: Giá trị không hợp lệ. Vui lòng nhập một trong " + Arrays.toString(enumClass.getEnumConstants()));
            }
        } while (true);
    }

    public static LocalDateTime compareDateTime(Scanner sc, LocalDateTime start, LocalDateTime end) {
        do {
            if (end.isAfter(start)) {
                return end;
            }

            System.err.println("Ngày kết thúc phải lớn hơn ngày bắt đầu");
            end = validateInputLocalDateTime(sc, "Nhập lại ngày kết thúc");
        }while (true);
    }
}
