package org.example.service.Impl;

import org.example.model.Student;
import org.example.service.StudentService;

public class StudentServiceMock implements StudentService {
    @Override
    public void createStudent(Student student) {
        System.out.println("Création d'un étudiant (mock) : " + student);
    }

    @Override
    public Student findStudentById(int id) {
        System.out.println("Recherche d'un étudiant (mock) avec l'ID : " + id);
        return null;
    }

    @Override
    public void displayStudent(Student student) {
        System.out.println("Affichage d'un étudiant (mock) : " + student);
    }
}
