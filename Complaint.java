package com.hostelmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="complaints")
public class Complaint {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="student_id", nullable=false)
    private Student student;
    @Column(nullable=false) private String title;
    @Column(nullable=false, length=1000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ComplaintStatus status = ComplaintStatus.OPEN;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Complaint() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public ComplaintStatus getStatus(){return status;} public void setStatus(ComplaintStatus v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
