package org.example;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Initialisation du contexte Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        // Récupération du bean StudentService à partir du contexte Spring
        StudentService studentService = context.getBean(StudentService.class);

        // Création d'un étudiant
        Student student1 = new Student(1, "Douae", "EL HILA");

        // Enregistrement de l'étudiant
        studentService.createStudent(student1);

        // Recherche de l'étudiant par ID
        Student retrievedStudent = studentService.findStudentById(1);

        if (retrievedStudent != null) {
            studentService.displayStudent(retrievedStudent);
            System.out.println("Etudiant recherché : " + retrievedStudent.getFirstName() + " " + retrievedStudent.getLastName() );
        } else {
            System.out.println("Aucun étudiant trouvé avec l'ID spécifié.");
        }

        // Fermeture du contexte Spring
        context.close();
    }
}
