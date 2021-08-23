package service;

import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    //private static List<Student> studentsDB = new ArrayList<>();
    private static final List<Student> studentsDB = new ArrayList<>();

    static  {

        //Add some dummy data set
        Student s1 = new Student("123456789V", "Kamal", "Galle", LocalDate.of(1996,05,01), "077-1234567", "abc@ijse.lk");
        Student s2 = new Student("223456789V", "Amara", "Matara", LocalDate.of(1989,10,01), "071-3456789", "abc@gmail.lk");
        Student s3 = new Student("323456789V", "Sunil", "Panadura", LocalDate.now(), "078-3456789", "abc@holmail.lk");
        Student s4 = new Student("423456789V", "Ranil", "Matara", LocalDate.of(1989,10,01), "075-3456789", "abc@yahoo.lk");
        studentsDB.add(s1);
        studentsDB.add(s2);
        studentsDB.add(s3);
        studentsDB.add(s4);
    }


    public void saveStudent(Student student) {
        studentsDB.add(student);
    }

    public void updateStudent(Student student) {
        Student s = findStudent(student.getNic());
        int index = studentsDB.indexOf(s);
        studentsDB.set(index, student);
    }

    public void deleteStudent(String nic) {
        Student student = findStudent(nic);
        studentsDB.remove(student);
    }

    public List<Student> findAllStudents() {
        return studentsDB;
    }

    public Student findStudent(String nic) {
        for (Student student : studentsDB) {

            if (student.getNic().equals(nic)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudents(String query) {
        List<Student> result = new ArrayList<>();

        for (Student student : studentsDB) {

            if (student.getNic().contains(query) || student.getFullName().contains(query)
                    || student.getAddress().contains(query) || student.getEmail().contains(query) ||
                    student.getDateOfBirth().toString().contains(query) || student.getContact().contains(query)) {
                result.add(student);
            }
        }
        return result;
    }

}


