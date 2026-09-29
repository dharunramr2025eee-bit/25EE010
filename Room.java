package com.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    @Column(nullable=false)
    private Integer capacity;
    @Column(nullable=false)
    private Integer occupiedBeds = 0;

    public Room() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getRoomNumber(){return roomNumber;} public void setRoomNumber(String v){roomNumber=v;}
    public RoomType getType(){return type;} public void setType(RoomType v){type=v;}
    public Integer getCapacity(){return capacity;} public void setCapacity(Integer v){capacity=v;}
    public Integer getOccupiedBeds(){return occupiedBeds;} public void setOccupiedBeds(Integer v){occupiedBeds=v;}
}
