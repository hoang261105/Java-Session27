package validate;

import entity.Person;
import presentation.UniversityManager;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PersonValidator {
    public static int validateInputAge(Scanner sc, String message, int min){
        System.out.println(message);

        do {
            try {
                int input = Integer.parseInt(sc.nextLine());

                if(input > min){
                    return input;
                }

                System.err.println("Tuổi phải lớn hon " + min + ". Vui lòng nhập lại");
            } catch (NumberFormatException e) {
                System.err.println("Dữ liệu không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static String validateInputEmail(Scanner sc, String message, String regex){
        System.out.println(message);

        do {
            try {
                String input = sc.nextLine();

                if(Pattern.matches(regex, input)){
                    return input;
                }

                System.err.println("Định dạng email không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static String validateInputPhone(Scanner sc, String message, String regex){
        System.out.println(message);

        do {
            try {
                String input = sc.nextLine();

                if(Pattern.matches(regex, input)){
                    return input;
                }

                System.err.println("Định dạng số điện thoại không hợp lệ. Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    public static String validateExistPerson(Scanner sc, String value, String type){
        boolean exist = false;
        do {
            switch (type) {
                case "address":
                    String name = value;
                    List<Person> existName = UniversityManager.personList.stream()
                            .filter(person -> person.getName().equals(name))
                            .toList();

                    if(!existName.isEmpty()){
                        exist = true;
                        break;
                    }
                    break;
                case "phoneNumber":
                    String phone = value;
                    List<Person> existPhone = UniversityManager.personList.stream()
                            .filter(person -> person.getName().equals(phone))
                            .toList();

                    if(!existPhone.isEmpty()){
                        exist = true;
                        break;
                    }
                    break;
            }

            if (!exist) {
                return value;
            }

            System.err.println("Dữ liệu đã tồn tại. Vui lòng nhập lại");
            value = sc.nextLine();
        }while (true);
    }
}
