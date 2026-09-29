package com.hostelmanagement.service;

import com.hostelmanagement.entity.Room;
import com.hostelmanagement.entity.Student;
import com.hostelmanagement.repository.RoomRepository;
import com.hostelmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository students;
    private final RoomRepository rooms;
    public StudentService(StudentRepository students, RoomRepository rooms){this.students=students;this.rooms=rooms;}

    public List<Student> all(){return students.findAll();}

    public Student create(Student student, Long roomId){
        if(roomId != null){
            Room room=rooms.findById(roomId).orElseThrow(()->new RuntimeException("Room not found"));
            if(room.getOccupiedBeds() >= room.getCapacity()) throw new RuntimeException("Room is full");
            room.setOccupiedBeds(room.getOccupiedBeds()+1);
            rooms.save(room);
            student.setRoom(room);
        }
        return students.save(student);
    }

    public void delete(Long id){
        Student s=students.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        if(s.getRoom()!=null){
            Room r=s.getRoom();
            r.setOccupiedBeds(Math.max(0,r.getOccupiedBeds()-1));
            rooms.save(r);
        }
        students.delete(s);
    }
}
