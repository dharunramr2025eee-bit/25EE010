package com.hostelmanagement.service;

import com.hostelmanagement.entity.*;
import com.hostelmanagement.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository complaints;
    private final StudentRepository students;
    public ComplaintService(ComplaintRepository complaints, StudentRepository students){this.complaints=complaints;this.students=students;}

    public List<Complaint> all(){return complaints.findAll();}
    public Complaint create(Long studentId, String title, String description){
        Student s=students.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
        Complaint c=new Complaint();
        c.setStudent(s); c.setTitle(title); c.setDescription(description); c.setStatus(ComplaintStatus.OPEN);
        return complaints.save(c);
    }
    public Complaint resolve(Long id){
        Complaint c=complaints.findById(id).orElseThrow(()->new RuntimeException("Complaint not found"));
        c.setStatus(ComplaintStatus.RESOLVED);
        return complaints.save(c);
    }
}
