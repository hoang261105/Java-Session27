package business.service.student;

import business.dao.student.StudentDao;
import business.dao.student.StudentDaoImp;
import business.model.Student;

import java.util.List;

public class StudentServiceImp implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImp() {
        this.studentDao = new StudentDaoImp();
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentDao.delete(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public int getCountStudentByAge(int age) {
        return studentDao.countStudentByAge(age);
    }
}
