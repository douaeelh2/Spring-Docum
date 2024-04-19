package org.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryStudentRepository implements StudentRepository {
    private final Map<Integer, Student> students = new HashMap<>();

    @Override
    public void save(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public Student findById(int id) {
        return students.get(id);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}

