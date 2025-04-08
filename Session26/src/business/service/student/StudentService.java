package business.service.student;

import business.model.Student;
import business.service.AppService;

public interface StudentService extends AppService<Student> {
    Student findById(int id);

    int getCountStudentByAge(int age);
}
