

package com.online.test.onlinetest.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_EXAMS")
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "TITLE", nullable = false, length = 100)    
    private String title;
    @Column(name = "DESCRIPTION")    
    private String description;
    @Column(name = "TIME_LIMIT")    
    private short timeLimit;
    @Column(name = "MINIMUN_PASSING_SCORE", precision = 4, scale = 2)    
    private double minimumPassingScore;
    @Column(name = "NUMBER_OF_QUESTIONS")    
    private short numberOfQuestions;
    @Column(name = "INSTRUCTIONS")    
    private String instructions;  

    @Column(name = "CREATED_DATE")    
    private Calendar createdDate;
    @Column(name = "CREATED_BY")    
    private String createdBy;  

    @Column(name = "UPDATED_DATE")    
    private Calendar updatedDate;
    @Column(name = "UPDATED_BY")    
    private String updatedBy;  

    @PrePersist
    public void prePersist(){
        createdDate = Calendar.getInstance();
        createdBy = "user1";
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate = Calendar.getInstance();
        updatedBy = "user2";
    }
    
    @OneToMany(mappedBy="exam") //nombre del atributo en la clase B       
    private List<Question> questions;

}
