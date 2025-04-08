package business.service;

import business.model.Student;

import java.util.List;

public interface AppService<T>{
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    Student findById(int id);
}
