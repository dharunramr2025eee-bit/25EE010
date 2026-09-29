package com.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String name;
    @Column(nullable=false, unique=true) private String email;
    private String phone;
    private String department;
    private Integer year;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="room_id")
    private Room room;

    public Student() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
    public Integer getYear(){return year;} public void setYear(Integer v){year=v;}
    public Room getRoom(){return room;} public void setRoom(Room v){room=v;}
}
