package validate;

import entity.ClassRoom;
import presentation.UniversityManager;

import java.util.List;
import java.util.Scanner;

public class ClassRoomValidator {
    public static String validateExistClassroom(Scanner sc, String value) {
        do {
            String classRoomName = value;

            List<ClassRoom> existClassRoom = UniversityManager.classRoomList.stream()
                    .filter(classRoom -> classRoom.getClassroomName().equals(classRoomName))
                    .toList();

            if (existClassRoom.isEmpty()){
                return classRoomName;
            }

            System.err.println("Tên lớp học đã tồn tại. Vui lòng nhập lại!");
            value = sc.nextLine();
        }while (true);
    }
}
