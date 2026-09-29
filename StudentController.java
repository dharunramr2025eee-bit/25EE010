package com.hostelmanagement.controller;

import com.hostelmanagement.entity.Student;
import com.hostelmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service){this.service=service;}

    @GetMapping public List<Map<String,Object>> all(){
        return service.all().stream().map(this::dto).toList();
    }

    @PostMapping public Map<String,Object> create(@RequestBody Map<String,Object> body){
        Student s=new Student();
        s.setName((String)body.get("name"));
        s.setEmail((String)body.get("email"));
        s.setPhone((String)body.get("phone"));
        s.setDepartment((String)body.get("department"));
        Object y=body.get("year"); if(y!=null) s.setYear(((Number)y).intValue());
        Object r=body.get("roomId");
        Student saved=service.create(s, r==null?null:((Number)r).longValue());
        return dto(saved);
    }

    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}

    private Map<String,Object> dto(Student s){
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("id",s.getId()); m.put("name",s.getName()); m.put("email",s.getEmail());
        m.put("phone",s.getPhone()); m.put("department",s.getDepartment()); m.put("year",s.getYear());
        m.put("roomId",s.getRoom()==null?null:s.getRoom().getId());
        m.put("roomNumber",s.getRoom()==null?null:s.getRoom().getRoomNumber());
        return m;
    }
}
