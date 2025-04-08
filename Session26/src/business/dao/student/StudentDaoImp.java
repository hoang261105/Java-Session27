package business.dao.student;

import business.config.ConnectionDB;
import business.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements StudentDao {
    @Override
    public int countStudentByAge(int age) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call getCountStudentByAge(?,?)}");

            cstmt.registerOutParameter(2, Types.INTEGER);

            cstmt.execute();

            // Lấy dữ liệu tham số trả ra
            return cstmt.getInt(2);

        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return 0;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Student student = null;

        try {
            // Khởi tạo đối tượng connection làm việc với db
            conn = ConnectionDB.openConnection();

            // Khởi tạo đối tượng csmt từ conn và gọi procedure đe thao tác dữ liêu
            cstmt = conn.prepareCall("{call findStudentById(?)}");

            // Set giá tr cho các tham số vào
            cstmt.setInt(1, id);
            // Đăng ký kiểu dữ liệu cho các tham số ra
            // Thực hieenk procedure và xử lí kết quả trả về;

            /*
            1. excuteQuery: Thực hiện các procedure thực hiện SELECT
            2. excuteUpdate(): Thực hiện các procedure insert, update, delete mà không có tham số trả ra
            3. excecure(): Thực hiện các procedure insert, update, delete mà có tham số trả ra
             */
            ResultSet rs = cstmt.executeQuery();

            if(rs.next()) {
                student = new Student();
                student.setId(rs.getInt("studentId"));
                student.setName(rs.getString("studentName"));
                student.setAge(rs.getInt("studentAge"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }

        return student;
    }

    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> listStudents = null;

        try {
            // Khởi tạo đối tượng connection làm việc với db
            conn = ConnectionDB.openConnection();

            // Khởi tạo đối tượng csmt từ conn và gọi procedure đe thao tác dữ liêu
            cstmt = conn.prepareCall("{call find_all_Student()}");

            // Set giá tr cho các tham số vào
            // Đăng ký kiểu dữ liệu cho các tham số ra
            // Thực hieenk procedure và xử lí kết quả trả về;

            /*
            1. excuteQuery: Thực hiện các procedure thực hiện SELECT
            2. excuteUpdate(): Thực hiện các procedure insert, update, delete mà không có tham số trả ra
            3. excecure(): Thực hiện các procedure insert, update, delete mà có tham số trả ra
             */
            ResultSet rs = cstmt.executeQuery();
            listStudents = new ArrayList<>();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("studentId"));
                student.setName(rs.getString("studentName"));
                student.setAge(rs.getInt("studentAge"));
                listStudents.add(student);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }

        return listStudents;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call createStudent(?,?)}");

            cstmt.setString(1, student.getName());
            cstmt.setInt(2, student.getAge());

            cstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }

        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call updateStudent(?,?,?)}");

            cstmt.setInt(1, student.getId());
            cstmt.setString(2, student.getName());
            cstmt.setInt(3, student.getAge());

            cstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }

        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call deleteStudent(?)}");

            cstmt.setInt(1, student.getId());

            cstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }

        return false;
    }
}
