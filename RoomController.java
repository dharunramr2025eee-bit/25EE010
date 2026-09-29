package com.hostelmanagement.controller;

import com.hostelmanagement.entity.Room;
import com.hostelmanagement.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service){this.service=service;}
    @GetMapping public List<Room> all(){return service.all();}
    @PostMapping public Room create(@RequestBody Room room){return service.create(room);}
    @GetMapping("/{id}") public Room get(@PathVariable Long id){return service.get(id);}
}
