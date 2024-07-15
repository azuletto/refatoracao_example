package src.controller;

import src.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void editStudent(int id, Student newStudent) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newStudent.getName());
                student.setUsername(newStudent.getUsername());
                student.setPassword(newStudent.getPassword());
                break;
            }
        }
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
