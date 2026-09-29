package com.hostelmanagement.service;

import com.hostelmanagement.entity.Room;
import com.hostelmanagement.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository repo;
    public RoomService(RoomRepository repo){this.repo=repo;}
    public List<Room> all(){return repo.findAll();}
    public Room create(Room room){
        if(room.getOccupiedBeds()==null) room.setOccupiedBeds(0);
        return repo.save(room);
    }
    public Room get(Long id){return repo.findById(id).orElseThrow(()->new RuntimeException("Room not found"));}
}
