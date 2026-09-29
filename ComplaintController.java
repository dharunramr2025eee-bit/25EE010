package com.hostelmanagement.controller;

import com.hostelmanagement.entity.Complaint;
import com.hostelmanagement.service.ComplaintService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService service;
    public ComplaintController(ComplaintService service){this.service=service;}

    @GetMapping public List<Map<String,Object>> all(){return service.all().stream().map(this::dto).toList();}

    @PostMapping public Map<String,Object> create(@RequestBody Map<String,Object> body){
        Long studentId=((Number)body.get("studentId")).longValue();
        Complaint c=service.create(studentId,(String)body.get("title"),(String)body.get("description"));
        return dto(c);
    }

    @PutMapping("/{id}/resolve") public Map<String,Object> resolve(@PathVariable Long id){return dto(service.resolve(id));}

    private Map<String,Object> dto(Complaint c){
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("id",c.getId()); m.put("studentId",c.getStudent().getId()); m.put("studentName",c.getStudent().getName());
        m.put("title",c.getTitle()); m.put("description",c.getDescription()); m.put("status",c.getStatus());
        m.put("createdAt",c.getCreatedAt());
        return m;
    }
}
